package neuro.cryptocurrencies.domain.entity

data class CoinTicker(
	val id: String,
	val name: String,
	val rank: Int,
	val symbol: String,
	val price: Double,
	val percentChange24h: Double
)