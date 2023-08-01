package neuro.cryptocurrencies.domain.repository.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagDetailsRepository {
	suspend fun getTagDetails(tagId: String): TagDetails?
}