package neuro.coin.paprika.data.repository

import neuro.coin.paprika.data.api.CoinPaprikaApi
import neuro.coin.paprika.data.mapper.toDomain
import neuro.coin.paprika.domain.model.Coin
import neuro.coin.paprika.domain.repository.GetCoinsRepository
import neuro.coin.paprika.domain.usecase.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class GetCoinsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) : GetCoinsRepository {
	override suspend fun getCoins(): List<Coin> {
		return try {
			coinPaprikaApi.getCoins().map { it.toDomain() }
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}