package neuro.coin.paprika.presentation.viewmodel.coins

import neuro.coin.paprika.presentation.model.CoinModel

data class CoinListState(
	val coins: List<CoinModel>,
	val error: String = ""
)
