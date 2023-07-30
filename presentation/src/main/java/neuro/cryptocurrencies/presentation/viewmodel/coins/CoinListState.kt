package neuro.cryptocurrencies.presentation.viewmodel.coins

import neuro.cryptocurrencies.presentation.model.CoinModel

data class CoinListState(
	val coins: List<CoinModel>? = null,
	val error: String = "",
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false
)
