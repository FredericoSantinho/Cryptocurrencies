package neuro.coin.paprika.domain.model

data class CoinDetails(
	val description: String,
	val hashAlgorithm: String,
	val id: String,
	val isActive: Boolean,
	val logo: String,
	val name: String,
	val openSource: Boolean,
	val proofType: String,
	val rank: Int,
	val started_at: String,
	val symbol: String,
	val tags: List<String>,
	val team: List<Team>,
	val type: String,
	val whitepaper: String
)