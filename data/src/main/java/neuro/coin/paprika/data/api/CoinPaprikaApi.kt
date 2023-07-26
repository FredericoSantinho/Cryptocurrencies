package neuro.coin.paprika.data.api

import neuro.coin.paprika.data.model.coin.CoinDto
import neuro.coin.paprika.data.model.coin.details.CoinDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
	@GET("/v1/coins")
	suspend fun getCoins(): List<CoinDto>

	@GET("/v1/coins/{coinId}")
	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}