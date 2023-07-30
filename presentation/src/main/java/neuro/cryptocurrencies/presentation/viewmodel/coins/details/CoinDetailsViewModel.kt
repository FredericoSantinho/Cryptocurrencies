package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation

class CoinDetailsViewModel(
	private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
	savedStateHandle: SavedStateHandle
) : ViewModel() {
	private val _uiState = mutableStateOf(CoinDetailsState(isLoading = true))
	val uiState: State<CoinDetailsState> = _uiState

	init {
		savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
			getCoinDetails(it)
		}
	}

	private fun getCoinDetails(coinId: String) {
		viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
			_uiState.value =
				uiState.value.copy(
					error = throwable.localizedMessage ?: "Unexpected error occurred!",
					isLoading = false
				)
		}) {
			val coinDetailsModel = getCoinDetailsUseCase.execute(coinId).toPresentation()
			_uiState.value = uiState.value.copy(coinDetailsModel = coinDetailsModel, isLoading = false)
		}
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}