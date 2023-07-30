package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State

interface CoinListViewModel {
	val uiState: State<CoinListState>
	val uiEvent: State<CoinListViewModelImpl.UiEvent?>

	fun onCoinClick(coinId: String)
	fun eventConsumed()
	fun onRefresh()
	fun errorShown()
	fun onSearchTerm(searchTerm: String)
}