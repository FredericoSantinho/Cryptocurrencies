package neuro.cryptocurrencies.data.api

import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
	@GET("/v1/tickers")
	suspend fun getCoinsTickers(): List<CoinTickerDto>

	@GET("/v1/coins/{coinId}")
	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}