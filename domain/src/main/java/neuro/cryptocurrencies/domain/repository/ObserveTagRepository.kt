package neuro.cryptocurrencies.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagRepository {
	fun observeTag(tagId: String): Flow<TagDetails>
}