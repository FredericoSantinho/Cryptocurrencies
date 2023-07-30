package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.cryptocurrencies.presentation.model.CoinModel

class DummyCoinListViewModel : CoinListViewModel {
	override val uiState: State<CoinListState> =
		mutableStateOf(
			CoinListState(
				listOf(
					CoinModel("bitcoin", true, "Bitcoin", 1, "BTC"),
					CoinModel("ethereum", true, "Ethereum", 2, "ETH")
				)
			)
		)
	override val uiEvent: State<CoinListViewModelImpl.UiEvent?> = mutableStateOf(null)

	override fun onCoinClick(coinId: String) {
	}

	override fun eventConsumed() {
	}

	override fun onRefresh() {
	}

	override fun errorShown() {
	}

	override fun onSearchTerm(searchTerm: String) {
	}
}