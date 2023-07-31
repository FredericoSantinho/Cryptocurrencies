package neuro.cryptocurrencies.domain.repository

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagRepository {
	suspend fun getTag(tagId: String): TagDetails
}