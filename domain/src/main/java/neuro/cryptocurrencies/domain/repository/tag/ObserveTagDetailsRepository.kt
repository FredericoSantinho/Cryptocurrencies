package neuro.cryptocurrencies.domain.repository.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagDetailsRepository {
	/**
	 * Observe cached tag details.
	 * @param tagId tag id.
	 * @return a Flow that will emit tag details every time there is a change in cache.
	 */
	fun observeTagDetails(tagId: String): Flow<TagDetails>
}