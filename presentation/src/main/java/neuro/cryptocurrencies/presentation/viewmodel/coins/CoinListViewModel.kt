package neuro.cryptocurrencies.presentation.viewmodel.coins

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.SharedFlow

interface CoinListViewModel {
	val uiState: State<CoinListState>
	val uiEvent: SharedFlow<CoinListViewModelImpl.UiEvent>

	fun onCoinClick(coinId: String)
	fun onRefresh()
	fun errorShown()
	fun onSearchTerm(searchTerm: String)
}