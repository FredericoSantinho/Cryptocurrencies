package neuro.cryptocurrencies.presentation.model

data class CoinDetailsModel(
	val id: String,
	val description: String,
	val hashAlgorithm: String,
	val logo: String,
	val name: String,
	val openSource: Boolean,
	val proofType: String,
	val rank: Int,
	val symbol: String,
	val tags: List<TagModel>,
	val team: List<TeamMemberModel>,
	val type: String
)
