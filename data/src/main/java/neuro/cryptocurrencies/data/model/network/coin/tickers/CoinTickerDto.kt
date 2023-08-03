package neuro.cryptocurrencies.data.model.network.coin.tickers


import com.google.gson.annotations.SerializedName

data class CoinTickerDto(
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("quotes")
	val quotes: Quotes,
	@SerializedName("rank")
	val rank: Int,
	@SerializedName("symbol")
	val symbol: String
)