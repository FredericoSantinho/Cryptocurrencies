package neuro.cryptocurrencies.presentation.model

data class CoinTickerModel(
	val id: String,
	val name: String?,
	val rank: Int?,
	val symbol: String?,
	val price: String
)