package neuro.cryptocurrencies.data.repository

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.GetTagRepository

class GetTagRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) : GetTagRepository {
	override suspend fun getTag(tagId: String): TagDetails {
		return coinPaprikaApi.getTag(tagId).toDomain()
	}
}