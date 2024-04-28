package neuro.cryptocurrencies.presentation.viewmodel.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import neuro.cryptocurrencies.domain.usecase.coinTickers.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.ui.base.BaseViewModel

class CoinListViewModelImpl(
	private val observeCoinsTickersUseCase: ObserveCoinsTickersUseCase,
	private val fetchCoinsTickersUseCase: FetchCoinsTickersUseCase,
	private val hasCachedCoinsTickersUseCase: HasCachedCoinsTickersUseCase,
	private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(), CoinListViewModel {
	private val _uiState = mutableStateOf(CoinListState(isLoading = true))
	override val uiState: State<CoinListState> = _uiState
	private val _uiEvent = MutableSharedFlow<UiEvent>()
	override val uiEvent: SharedFlow<UiEvent> = _uiEvent

	private val coinTickers = MutableStateFlow<List<CoinTickerModel>?>(null)
	private val searchTerm = MutableStateFlow("")

	init {
		setupCoinsTickers()
		updateUiState()
	}

	override fun onCoinClick(coinId: String) {
		viewModelScope.launch {
			_uiEvent.emit(UiEvent.NavigateToDetails(coinId))
		}
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		fetchCoinsTickers()
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true, isErrorState = false)
		fetchCoinsTickers()
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = ErrorMessage.Empty)
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
							coins = filteredCoins.toImmutableList(),
							isLoading = false,
							isRefreshing = false,
							isErrorState = false
						)
				}
			}
		}.launchIn(viewModelScope)
	}

	private fun setupCoinsTickers() {
		observeCoinsTickers()
		fetchCoinsTickers()
	}

	private fun observeCoinsTickers() {
		observeCoinsTickersUseCase.execute().map { it.toPresentation() }
			.onEach { coinModels ->
				if (coinTickers.value != null) {
					// To allow stateFlow emission when a previous value equal to the new one exists in order
					// to finish loading.
					coinTickers.value = emptyList()
				}
				coinTickers.value = coinModels
			}.catch {
				_uiState.value =
					uiState.value.copy(
						isErrorState = true,
						errorMessage = it.message?.let { ErrorMessage.GivenMessage(it) }
							?: ErrorMessage.UnexpectedErrorOccurred,
						isLoading = false,
						isRefreshing = false
					)
			}.launchIn(viewModelScope)
	}

	private fun fetchCoinsTickers() {
		viewModelScope.launch(
			ioDispatcher +
					CoroutineExceptionHandler { _, throwable ->
						// In case a network error occurs.
						viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable1 ->
							// In case a database error occurs.
							viewModelScope.launch(Dispatchers.Main) {
								_uiState.value =
									uiState.value.copy(
										isErrorState = true,
										errorMessage = throwable1.message?.let { ErrorMessage.GivenMessage(it) }
											?: ErrorMessage.UnexpectedErrorOccurred,
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
											isErrorState = true,
											errorMessage = throwable.message?.let { ErrorMessage.GivenMessage(it) }
												?: ErrorMessage.UnexpectedErrorOccurred,
											isLoading = false,
											isRefreshing = false
										)
								} else {
									if (uiState.value.isRefreshing) {
										_uiState.value =
											uiState.value.copy(
												errorMessage = throwable.message?.let { ErrorMessage.GivenMessage(it) }
													?: ErrorMessage.UnexpectedErrorOccurred,
												isLoading = false,
												isRefreshing = false
											)
									}
								}
							}
						}
					},
		) {
			fetchCoinsTickersUseCase.execute()
		}
	}

	sealed class UiEvent {
		data class NavigateToDetails(val coinId: String) : UiEvent()
	}
}
