package neuro.cryptocurrencies.data.repository

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.mapper.toRoomCoin
import neuro.cryptocurrencies.domain.repository.FetchCoinsRepository
import neuro.cryptocurrencies.domain.usecase.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class FetchCoinsRepositoryImpl(
	private val coinPaprikaApi: CoinPaprikaApi,
	private val coinDao: CoinDao
) : FetchCoinsRepository {
	override suspend fun fetchCoins() {
		try {
			val roomCoins = coinPaprikaApi.getCoins().filter { it.rank != 0 }.map { it.toRoomCoin() }
			coinDao.upsertContact(roomCoins)
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}