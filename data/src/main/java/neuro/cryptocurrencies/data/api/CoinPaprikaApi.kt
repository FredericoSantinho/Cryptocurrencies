package neuro.cryptocurrencies.data.api

import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import neuro.cryptocurrencies.data.model.network.tag.TagDetailsDto
import neuro.cryptocurrencies.data.model.network.team.TeamMemberDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

interface CoinPaprikaApi {
	@Throws(IOException::class)
	@GET("/v1/tickers")
	suspend fun getCoinsTickers(): List<CoinTickerDto>

	@Throws(IOException::class)
	@GET("/v1/tickers/{coinId}")
	suspend fun getCoinTicker(@Path("coinId") coinId: String): CoinTickerDto

	@Throws(IOException::class)
	@GET("/v1/coins/{coinId}")
	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto

	@Throws(IOException::class)
	@GET("/v1/tags/{tagId}")
	suspend fun getTagDetails(@Path("tagId") tagId: String): TagDetailsDto

	@Throws(IOException::class)
	@GET("/v1/people/{teamMemberId}")
	suspend fun getTeamMemberDetails(@Path("teamMemberId") teamMemberId: String): TeamMemberDetailsDto
}