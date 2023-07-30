package neuro.cryptocurrencies.data.model.network.coin.details


import com.google.gson.annotations.SerializedName

data class Whitepaper(
	@SerializedName("link")
	val link: String,
	@SerializedName("thumbnail")
	val thumbnail: String
)