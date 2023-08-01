package neuro.cryptocurrencies.data.repository.coin

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.mapper.network.toDatabase
import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsTickersRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class FetchCoinsTickersRepositoryImpl(
	private val coinPaprikaApi: CoinPaprikaApi,
	private val coinDao: CoinDao
) : FetchCoinsTickersRepository {
	override suspend fun fetchCoinsTickers() {
		try {
			val roomCoins =
				coinPaprikaApi.getCoinsTickers().filter { it.rank != 0 }.map { it.toDatabase() }
			coinDao.upsertCoinTickers(roomCoins)
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}