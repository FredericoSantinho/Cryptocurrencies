package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.CoinTickerModel

class CoinListViewModelImpl(
	private val observeCoinsTickersUseCase: ObserveCoinsTickersUseCase,
	private val fetchCoinsTickersUseCase: FetchCoinsTickersUseCase,
	private val hasCachedCoinsTickersUseCase: HasCachedCoinsTickersUseCase
) : ViewModel(), CoinListViewModel {
	private val _uiState = mutableStateOf(CoinListState(isLoading = true))
	override val uiState: State<CoinListState> = _uiState
	private val _uiEvent = MutableSharedFlow<UiEvent>()
	override val uiEvent: SharedFlow<UiEvent> = _uiEvent

	private val coinTickers = MutableStateFlow<List<CoinTickerModel>?>(null)
	private val searchTerm = MutableStateFlow("")

	init {
		getCoins()
		updateUiState()
	}

	override fun onCoinClick(coinId: String) {
		viewModelScope.launch {
			_uiEvent.emit(UiEvent.NavigateToDetails(coinId))
		}
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		fetchCoins()
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true)
		fetchCoins()
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = "")
	}

	override fun onSearchTerm(searchTerm: String) {
		this.searchTerm.value = searchTerm
	}

	private fun updateUiState() {
		coinTickers.combine(searchTerm) { coinModels, searchTerm ->
			coinModels?.let {
				val filteredCoins = coinModels.filter { it.name.lowercase().contains(searchTerm) }
				if (filteredCoins.isNotEmpty()) {
					_uiState.value =
						uiState.value.copy(
							coins = filteredCoins,
							isLoading = false,
							isRefreshing = false,
							isError = false
						)
				}
			}
		}.catch {
			_uiState.value = uiState.value.copy(
				isError = true,
				errorMessage = it.message ?: "Unexpected error occurred!"
			)
		}.launchIn(viewModelScope)
	}

	private fun getCoins() {
		observeCoins()
		fetchCoins()
	}

	private fun observeCoins() {
		observeCoinsTickersUseCase.execute().map { it.toPresentation() }.flowOn(Dispatchers.IO)
			.onEach { coinModels ->
				if (coinTickers.value != null) {
					coinTickers.value = emptyList()
				}
				coinTickers.value = coinModels
			}.catch {
				_uiState.value =
					uiState.value.copy(
						isError = true,
						errorMessage = it.message ?: "Unexpected error occurred!",
						isLoading = false,
						isRefreshing = false
					)
			}.launchIn(viewModelScope)
	}

	private fun fetchCoins() {
		viewModelScope.launch(
			Dispatchers.IO +
					CoroutineExceptionHandler { _, throwable ->
						// In case a network error occurs.
						viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable1 ->
							// In case a database error occurs.
							viewModelScope.launch(Dispatchers.Main) {
								_uiState.value =
									uiState.value.copy(
										isError = true,
										errorMessage = throwable1.message ?: "Unexpected error occurred!",
										isLoading = false,
										isRefreshing = false
									)
							}
						}) {
							val hasCachedCoinsTickers = hasCachedCoinsTickersUseCase.execute()
							withContext(Dispatchers.Main) {
								if (!hasCachedCoinsTickers) {
									_uiState.value =
										uiState.value.copy(
											isError = true,
											errorMessage = throwable.message ?: "Unexpected error occurred!",
											isLoading = false,
											isRefreshing = false
										)
								} else {
									_uiState.value =
										uiState.value.copy(
											errorMessage = throwable.message ?: "Unexpected error occurred!",
											isLoading = false,
											isRefreshing = false
										)
								}
							}
						}
					},
		) {
			fetchCoinsTickersUseCase.fetchCoins()
		}
	}

	sealed class UiEvent {
		data class NavigateToDetails(val coinId: String) : UiEvent()
	}
}
