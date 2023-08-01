package neuro.cryptocurrencies.data.repository.coin

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class GetCoinTickersRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickersRepository {
	override suspend fun getCoinTickers(): List<CoinTicker> {
		try {
			return coinPaprikaApi.getCoinsTickers().filter { it.rank != 0 }.map { it.toDomain() }
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}