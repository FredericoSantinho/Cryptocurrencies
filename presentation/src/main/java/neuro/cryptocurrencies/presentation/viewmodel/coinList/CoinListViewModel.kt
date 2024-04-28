package neuro.cryptocurrencies.presentation.viewmodel.coinList

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.SharedFlow

interface CoinListViewModel {
	val uiState: State<CoinListState>
	val uiEvent: SharedFlow<CoinListViewModelImpl.UiEvent>

	fun onCoinClick(coinId: String)
	fun onRefresh()
	fun onRetry()
	fun errorShown()
	fun onSearchTerm(searchTerm: String)
}