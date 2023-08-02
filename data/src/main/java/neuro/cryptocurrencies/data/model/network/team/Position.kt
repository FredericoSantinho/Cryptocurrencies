package neuro.cryptocurrencies.data.model.network.team


import com.google.gson.annotations.SerializedName

data class Position(
	@SerializedName("coin_id")
	val coinId: String,
	@SerializedName("coin_name")
	val coinName: String,
	@SerializedName("coin_symbol")
	val coinSymbol: String,
	@SerializedName("position")
	val position: String
)