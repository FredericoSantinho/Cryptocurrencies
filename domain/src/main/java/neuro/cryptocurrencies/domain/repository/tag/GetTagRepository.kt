package neuro.cryptocurrencies.domain.repository.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagRepository {
	fun getTag(tagId: String): TagDetails?
}