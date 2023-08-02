package neuro.cryptocurrencies.data.model.network.team


import com.google.gson.annotations.SerializedName

data class Github(
	@SerializedName("followers")
	val followers: Int,
	@SerializedName("url")
	val url: String
)