package neuro.cryptocurrencies.presentation.viewmodel.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
import neuro.cryptocurrencies.domain.usecase.coinTickers.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CoinListViewModelImpl @Inject constructor(
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
		navigateToDetails(coinId)
	}

	override fun onRefresh() {
		setRefreshingState()
		fetchCoinsTickers()
	}

	override fun onRetry() {
		showLoading()
		fetchCoinsTickers()
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = ErrorMessage.Empty)
	}

	override fun onSearchTerm(searchTerm: String) {
		this.searchTerm.value = searchTerm
	}

	private fun showLoading() {
		_uiState.value = uiState.value.copy(isLoading = true, isErrorState = false)
	}

	private fun navigateToDetails(coinId: String) {
		viewModelScope.launch {
			_uiEvent.emit(UiEvent.NavigateToDetails(coinId))
		}
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
				showErrorAndSetErrorState(it)
			}.launchIn(viewModelScope)
	}

	private fun fetchCoinsTickers() {
		viewModelScope.launch(
			handleFetchCoinsTickersError(),
		) {
			fetchCoinsTickersUseCase.execute()
		}
	}

	private fun handleFetchCoinsTickersError() = CoroutineExceptionHandler { _, throwable ->
		viewModelScope.launch(CoroutineExceptionHandler { _, throwable1 ->
			// In case a database error occurs.
			showErrorAndSetErrorState(throwable1)
		}) {
			handleErrorIfNoCachedCoinsTickers(throwable)
		}
	}

	private suspend fun handleErrorIfNoCachedCoinsTickers(throwable: Throwable) {
		val hasCachedCoinsTickers = hasCachedCoinsTickersUseCase.execute()
		if (!hasCachedCoinsTickers) {
			showErrorAndSetErrorState(throwable)
		} else {
			if (uiState.value.isRefreshing) {
				showError(throwable)
			}
		}
	}

	private fun setRefreshingState() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
	}

	private fun updateUiState() {
		coinTickers.combine(searchTerm) { coinModels, searchTerm ->
			coinModels?.let {
				val filteredCoins = coinModels.filter { it.name.lowercase().contains(searchTerm) }
				if (filteredCoins.isNotEmpty()) {
					setCoins(filteredCoins)
				}
			}
		}.launchIn(viewModelScope)
	}

	private fun setCoins(filteredCoins: List<CoinTickerModel>) {
		_uiState.value =
			uiState.value.copy(
				coins = filteredCoins.toImmutableList(),
				isLoading = false,
				isRefreshing = false,
				isErrorState = false
			)
	}

	private fun showError(throwable: Throwable) {
		_uiState.value = uiState.value.copy(
			errorMessage = throwable.message?.let { ErrorMessage.GivenMessage(it) }
				?: ErrorMessage.UnexpectedErrorOccurred,
			isLoading = false,
			isRefreshing = false
		)
	}

	private fun showErrorAndSetErrorState(throwable: Throwable) {
		_uiState.value =
			uiState.value.copy(
				isErrorState = true,
				errorMessage = throwable.message?.let { ErrorMessage.GivenMessage(it) }
					?: ErrorMessage.UnexpectedErrorOccurred,
				isLoading = false,
				isRefreshing = false
			)
	}

	sealed class UiEvent {
		data class NavigateToDetails(val coinId: String) : UiEvent()
	}
}
