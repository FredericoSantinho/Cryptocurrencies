package neuro.cryptocurrencies.domain.repository.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagDetailsRepository {
	fun observeTagDetails(tagId: String): Flow<TagDetails>
}