package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsWithPriceUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation

class CoinDetailsViewModelImpl(
	private val getCoinDetailsWithPriceUseCase: GetCoinDetailsWithPriceUseCase,
	savedStateHandle: SavedStateHandle
) : ViewModel(), CoinDetailsViewModel {
	private val _uiState = mutableStateOf(CoinDetailsState(isLoading = true))
	override val uiState: State<CoinDetailsState> = _uiState

	private lateinit var coinId: String

	init {
		savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
			coinId = it
			getCoinDetails(it)
		}
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = "")
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true, isError = false)
		getCoinDetails(coinId)
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		getCoinDetails(coinId)
	}

	private fun getCoinDetails(coinId: String) {
		viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
			_uiState.value =
				uiState.value.copy(
					errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
					isError = true,
					isLoading = false,
					isRefreshing = false
				)
		}) {
			val coinDetailsWithPriceModel =
				getCoinDetailsWithPriceUseCase.execute(coinId, viewModelScope).toPresentation()
			_uiState.value =
				uiState.value.copy(
					coinDetailsWithPriceModel = coinDetailsWithPriceModel,
					isLoading = false,
					isError = false,
					isRefreshing = false
				)
		}
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}