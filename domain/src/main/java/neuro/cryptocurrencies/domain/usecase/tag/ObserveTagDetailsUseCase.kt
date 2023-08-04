package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagDetailsUseCase {
	/**
	 * Observe cached tag details.
	 * @param tagId tag id.
	 * @return a Flow that will emit tag details every time there is a change in cache.
	 */
	fun execute(tagId: String): Flow<TagDetails>
}