package neuro.cryptocurrencies.data.repository.tag

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mapper.network.toDatabase
import neuro.cryptocurrencies.domain.repository.tag.FetchTagDetailsRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import retrofit2.HttpException
import java.io.IOException

class FetchTagDetailsRepositoryImpl(
	private val coinPaprikaApi: CoinPaprikaApi,
	private val tagDetailsDao: TagDetailsDao
) : FetchTagDetailsRepository {
	override suspend fun fetchTagDetails(tagId: String) {
		try {
			val roomTagDetails = coinPaprikaApi.getTag(tagId).toDatabase()
			tagDetailsDao.upsertTagDetails(roomTagDetails)
		} catch (e: HttpException) {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}