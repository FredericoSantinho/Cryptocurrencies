package neuro.cryptocurrencies.data.model.network.coin.tickers


import com.google.gson.annotations.SerializedName

data class Quotes(
	@SerializedName("USD")
	val usd: USD
)