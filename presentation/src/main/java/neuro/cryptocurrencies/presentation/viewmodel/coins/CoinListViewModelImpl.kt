package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import neuro.cryptocurrencies.domain.usecase.FetchCoinsUseCase
import neuro.cryptocurrencies.domain.usecase.GetCoinsUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.CoinTickerModel

class CoinListViewModelImpl(
	private val getCoinsUseCase: GetCoinsUseCase,
	private val fetchCoinsUseCase: FetchCoinsUseCase
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

	override fun errorShown() {
		_uiState.value = uiState.value.copy(error = "")
	}

	override fun onSearchTerm(searchTerm: String) {
		this.searchTerm.value = searchTerm
	}

	private fun updateUiState() {
		coinTickers.combine(searchTerm) { coinModels, searchTerm ->
			coinModels?.let {
				val filteredCoins = coinModels.filter { it.name.lowercase().contains(searchTerm) }
				_uiState.value =
					uiState.value.copy(coins = filteredCoins, isLoading = false, isRefreshing = false)
			}
		}.catch {
			_uiState.value = uiState.value.copy(error = it.message ?: "Unexpected error occurred!")
		}.launchIn(viewModelScope)
	}

	private fun getCoins() {
		observeCoins()
		fetchCoins()
	}

	private fun observeCoins() {
		getCoinsUseCase.execute().map { it.toPresentation() }.onEach { coinModels ->
			coinTickers.value = emptyList()
			coinTickers.value = coinModels
		}.catch {
			_uiState.value =
				uiState.value.copy(
					error = it.message ?: "Unexpected error occurred!",
					isLoading = false,
					isRefreshing = false
				)
		}.launchIn(viewModelScope)
	}

	private fun fetchCoins() {
		viewModelScope.launch(
			CoroutineExceptionHandler { coroutineContext, throwable ->
				_uiState.value =
					uiState.value.copy(
						error = throwable.message ?: "Unexpected error occurred!",
						isLoading = false,
						isRefreshing = false
					)
			},
		) {
			fetchCoinsUseCase.fetchCoins()
		}
	}

	sealed class UiEvent {
		data class NavigateToDetails(val coinId: String) : UiEvent()
	}
}
