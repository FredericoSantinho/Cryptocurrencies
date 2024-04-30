package neuro.cryptocurrencies.presentation.viewmodel.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

class DummyCoinListViewModel : CoinListViewModel {
	override val uiState: State<CoinListState> =
		mutableStateOf(
			CoinListState(
				listOf(
					CoinTickerModel("bitcoin", "Bitcoin", 1, "BTC", "25421.53"),
					CoinTickerModel("ethereum", "Ethereum", 2, "ETH", "1543.43")
				).toImmutableList()
			)
		)
	override val uiEvent: SharedFlow<CoinListViewModel.UiEvent> = MutableSharedFlow()

	override val debounceTimer = DebounceTimer()

	override fun onCoinClick(coinId: String) {
	}

	override fun onRefresh() {
	}

	override fun onRetry() {

	}

	override fun errorShown() {
	}

	override fun onSearchTerm(searchTerm: String) {
	}
}