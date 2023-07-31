package neuro.cryptocurrencies.data.api

import neuro.cryptocurrencies.data.model.database.tag.TagDto
import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
	@GET("/v1/tickers")
	suspend fun getCoinsTickers(): List<CoinTickerDto>

	@GET("/v1/tickers/{coinId}")
	suspend fun getCoinTicker(@Path("coinId") coinId: String): CoinTickerDto

	@GET("/v1/coins/{coinId}")
	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto

	@GET("/v1/tags/{tagId}")
	suspend fun getTag(@Path("tagId") tagId: String): TagDto
}