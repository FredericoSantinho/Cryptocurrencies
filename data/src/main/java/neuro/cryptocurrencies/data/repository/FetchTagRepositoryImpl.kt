package neuro.cryptocurrencies.data.repository

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.mapper.network.toDatabase
import neuro.cryptocurrencies.domain.repository.FetchTagRepository
import neuro.cryptocurrencies.domain.usecase.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class FetchTagRepositoryImpl(
	private val coinPaprikaApi: CoinPaprikaApi,
	private val tagDao: TagDao
) : FetchTagRepository {
	override suspend fun fetchTag(tagId: String) {
		try {
			val roomTagDetails = coinPaprikaApi.getTag(tagId).toDatabase()
			tagDao.upsertTag(roomTagDetails)
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}