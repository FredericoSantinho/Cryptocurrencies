package neuro.cryptocurrencies.presentation.viewmodel.coinList

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.SharedFlow
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

interface CoinListViewModel {
	val uiState: State<CoinListState>
	val uiEvent: SharedFlow<CoinListViewModelImpl.UiEvent>

	val debounceTimer: DebounceTimer

	fun onCoinClick(coinId: String)
	fun onRefresh()
	fun onRetry()
	fun errorShown()
	fun onSearchTerm(searchTerm: String)
}