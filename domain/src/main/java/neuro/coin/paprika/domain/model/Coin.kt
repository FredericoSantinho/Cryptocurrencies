package neuro.coin.paprika.domain.model

data class Coin(
	val id: String,
	val is_active: Boolean,
	val name: String,
	val rank: Int,
	val symbol: String,
	val type: String
)
