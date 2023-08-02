package neuro.cryptocurrencies.data.repository.tag

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import retrofit2.HttpException
import java.io.IOException

class GetTagDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetTagDetailsRepository {
	override suspend fun getTagDetails(tagId: String): TagDetails {
		try {
			return coinPaprikaApi.getTag(tagId).toDomain()
		} catch (e: HttpException) {
			if (e.code() == 404) {
				throw NoDataAvailableException(e.localizedMessage ?: "Not found")
			} else {
				throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
			}
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}