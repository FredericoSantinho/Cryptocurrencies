package neuro.cryptocurrencies.domain.entity

data class CoinDetails(
	val id: String,
	val description: String,
	val hashAlgorithm: String,
	val logo: String,
	val name: String,
	val openSource: Boolean,
	val proofType: String,
	val rank: Int,
	val symbol: String,
	val tags: List<Tag>,
	val team: List<TeamMember>,
	val type: String
)