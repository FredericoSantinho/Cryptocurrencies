package neuro.cryptocurrencies.presentation.viewmodel.coins

import neuro.cryptocurrencies.presentation.model.CoinModel

data class CoinListState(
	val coins: List<CoinModel> = emptyList(),
	val error: String = "",
	val isLoading: Boolean = false
)
