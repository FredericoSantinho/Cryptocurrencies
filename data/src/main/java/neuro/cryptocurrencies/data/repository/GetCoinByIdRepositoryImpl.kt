package neuro.cryptocurrencies.data.repository

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.toDomain
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.GetCoinByIdRepository
import neuro.cryptocurrencies.domain.usecase.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class GetCoinByIdRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinByIdRepository {
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