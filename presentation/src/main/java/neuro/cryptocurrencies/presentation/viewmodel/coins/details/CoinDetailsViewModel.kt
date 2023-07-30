package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State

interface CoinDetailsViewModel {
	val uiState: State<CoinDetailsState>
}