package neuro.cryptocurrencies.data.model.network.coin.details


import com.google.gson.annotations.SerializedName

data class Team(
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("position")
	val position: String
)