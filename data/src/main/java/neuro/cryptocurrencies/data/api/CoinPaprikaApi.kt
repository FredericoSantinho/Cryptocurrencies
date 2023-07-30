package neuro.cryptocurrencies.data.api

import neuro.cryptocurrencies.data.model.network.coin.CoinDto
import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
	@GET("/v1/coins")
	suspend fun getCoins(): List<CoinDto>

	@GET("/v1/coins/{coinId}")
	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}