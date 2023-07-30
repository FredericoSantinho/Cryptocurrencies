package neuro.cryptocurrencies.presentation.model

data class CoinDetailsModel(
	val description: String,
	val hashAlgorithm: String,
	val id: String,
	val isActive: Boolean,
	val logo: String,
	val name: String,
	val openSource: Boolean,
	val proofType: String,
	val rank: Int,
	val symbol: String,
	val tags: List<String>,
	val team: List<TeamModel>,
	val type: String
)
