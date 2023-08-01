package neuro.cryptocurrencies.data.repository.coin.details

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class GetCoinDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinDetailsRepository {
	override suspend fun getCoinDetails(coinId: String): CoinDetails {
		try {
			return coinPaprikaApi.getCoinById(coinId).toDomain()
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}