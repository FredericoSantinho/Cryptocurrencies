package neuro.coin.paprika.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import neuro.coin.paprika.domain.usecase.FetchCoinsUseCase
import neuro.coin.paprika.domain.usecase.GetCoinsUseCase
import neuro.coin.paprika.presentation.mapper.toPresentation

class CoinListViewModel(
	private val getCoinsUseCase: GetCoinsUseCase,
	private val fetchCoinsUseCase: FetchCoinsUseCase
) : ViewModel() {
	private val _uiState = mutableStateOf(CoinListState(isLoading = true))
	val uiState: State<CoinListState> = _uiState
	private val _uiEvent = mutableStateOf<UiEvent?>(null)
	val uiEvent: State<UiEvent?> = _uiEvent

	init {
		getCoins()
	}

	private fun getCoins() {
		getCoinsUseCase.execute().map { it.toPresentation() }.onEach { coins ->
			_uiState.value = uiState.value.copy(coins = coins, isLoading = false)
		}.catch {
			_uiState.value =
				uiState.value.copy(error = it.message ?: "Unexpected error occurred!", isLoading = false)
		}.launchIn(viewModelScope)

		viewModelScope.launch(
			CoroutineExceptionHandler { coroutineContext, throwable ->
				_uiState.value =
					uiState.value.copy(
						error = throwable.message ?: "Unexpected error occurred!",
						isLoading = false
					)
			},
		) {
			fetchCoinsUseCase.fetchCoins()
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
