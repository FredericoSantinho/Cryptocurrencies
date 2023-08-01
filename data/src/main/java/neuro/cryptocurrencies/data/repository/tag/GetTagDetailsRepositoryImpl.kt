package neuro.cryptocurrencies.data.repository.tag

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository

class GetTagDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetTagDetailsRepository {
	override suspend fun getTagDetails(tagId: String): TagDetails {
		return coinPaprikaApi.getTag(tagId).toDomain()
	}
}