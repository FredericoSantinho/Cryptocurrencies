package neuro.cryptocurrencies.data.model.network.coin.tickers


import com.google.gson.annotations.SerializedName

data class USD(
	@SerializedName("price")
	val price: Double?
)