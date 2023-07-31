package neuro.cryptocurrencies.presentation.viewmodel.coins

import neuro.cryptocurrencies.presentation.model.CoinTickerModel

data class CoinListState(
	val coins: List<CoinTickerModel>? = null,
	val isError: Boolean = false,
	val errorMessage: String = "",
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false
)
