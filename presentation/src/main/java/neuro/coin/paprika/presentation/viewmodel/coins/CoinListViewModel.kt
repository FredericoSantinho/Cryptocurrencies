package neuro.coin.paprika.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import neuro.coin.paprika.domain.usecase.GetCoinsUseCase
import neuro.coin.paprika.presentation.mapper.toPresentation

class CoinListViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {
	private val _uiState = mutableStateOf(CoinListState(emptyList()))
	val uiState: State<CoinListState> = _uiState
	private val _uiEvent = mutableStateOf<UiEvent?>(null)
	val uiEvent: State<UiEvent?> = _uiEvent

	init {
		getCoins()
	}

	private fun getCoins() {
		viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
			_uiState.value = uiState.value.copy(error = throwable.message ?: "Unexpected error occurred!")
		}) {
			val coins = getCoinsUseCase.execute().map { it.toPresentation() }
			_uiState.value = uiState.value.copy(coins = coins)
		}
	}

	fun onCoinClick(coinId: String) {
		_uiEvent.value = UiEvent.NavigateToDetails(coinId)
	}

	fun eventConsumed() {
		_uiEvent.value = null
	}

	sealed class UiEvent {
		data class NavigateToDetails(val coinId: String) : UiEvent()
	}
}
