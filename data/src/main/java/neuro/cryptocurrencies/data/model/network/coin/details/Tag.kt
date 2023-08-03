package neuro.cryptocurrencies.data.model.network.coin.details


import com.google.gson.annotations.SerializedName

data class Tag(
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String
)