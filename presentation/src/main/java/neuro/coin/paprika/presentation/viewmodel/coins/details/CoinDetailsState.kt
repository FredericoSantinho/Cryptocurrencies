package neuro.coin.paprika.presentation.viewmodel.coins.details

import neuro.coin.paprika.presentation.model.CoinDetailsModel

data class CoinDetailsState(
	val coinDetailsModel: CoinDetailsModel? = null,
	val error: String = "",
	val isLoading: Boolean = false
)