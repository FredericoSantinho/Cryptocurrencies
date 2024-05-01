package neuro.cryptocurrencies.data.repository.network.tag

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.common.tryRequest
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import javax.inject.Inject

class GetTagDetailsRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
	GetTagDetailsRepository {
	override suspend fun getTagDetails(tagId: String): TagDetails {
		return tryRequest {
			coinPaprikaApi.getTagDetails(tagId).toDomain()
		}
	}
}