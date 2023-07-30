package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import neuro.cryptocurrencies.presentation.model.CoinDetailsModel

data class CoinDetailsState(
	val coinDetailsModel: CoinDetailsModel? = null,
	val error: String = "",
	val isLoading: Boolean = false
)