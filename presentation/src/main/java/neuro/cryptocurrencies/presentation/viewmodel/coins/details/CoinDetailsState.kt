package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel

data class CoinDetailsState(
	val coinDetailsWithPriceModel: CoinDetailsWithPriceModel? = null,
	val isError: Boolean = false,
	val errorMessage: String = "",
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false
)