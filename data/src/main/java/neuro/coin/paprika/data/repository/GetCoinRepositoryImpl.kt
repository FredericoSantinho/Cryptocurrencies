package neuro.coin.paprika.data.repository

import neuro.coin.paprika.data.api.CoinPaprikaApi
import neuro.coin.paprika.data.mapper.toDomain
import neuro.coin.paprika.domain.model.CoinDetails
import neuro.coin.paprika.domain.repository.GetCoinRepository
import neuro.coin.paprika.domain.usecase.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class GetCoinRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) : GetCoinRepository {
	override suspend fun getCoinById(coinId: String): CoinDetails {
		try {
			return coinPaprikaApi.getCoinById(coinId).toDomain()
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}