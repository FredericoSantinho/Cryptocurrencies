package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import neuro.cryptocurrencies.presentation.model.CoinTickerModel

class DummyCoinListViewModel : CoinListViewModel {
	override val uiState: State<CoinListState> =
		mutableStateOf(
			CoinListState(
				listOf(
					CoinTickerModel("bitcoin", "Bitcoin", 1, "BTC", "25421.53"),
					CoinTickerModel("ethereum", "Ethereum", 2, "ETH", "1543.43")
				)
			)
		)
	override val uiEvent: SharedFlow<CoinListViewModelImpl.UiEvent> = MutableSharedFlow()

	override fun onCoinClick(coinId: String) {
	}

	override fun onRefresh() {
	}

	override fun errorShown() {
	}

	override fun onSearchTerm(searchTerm: String) {
	}
}