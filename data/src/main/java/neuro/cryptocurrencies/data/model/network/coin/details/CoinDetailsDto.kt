package neuro.cryptocurrencies.data.model.network.coin.details


import com.google.gson.annotations.SerializedName

data class CoinDetailsDto(
	@SerializedName("description")
	val description: String?,
	@SerializedName("hash_algorithm")
	val hashAlgorithm: String?,
	@SerializedName("id")
	val id: String,
	@SerializedName("logo")
	val logo: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("open_source")
	val openSource: Boolean,
	@SerializedName("proof_type")
	val proofType: String?,
	@SerializedName("rank")
	val rank: Int,
	@SerializedName("symbol")
	val symbol: String,
	@SerializedName("tags")
	val tags: List<Tag>?,
	@SerializedName("team")
	val team: List<Team>,
	@SerializedName("type")
	val type: String
)