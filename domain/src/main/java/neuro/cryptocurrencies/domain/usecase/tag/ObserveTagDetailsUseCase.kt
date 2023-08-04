package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagDetailsUseCase {
	/**
	 * Observe cached tag details.
	 * @return a Flow that will emit a TagDetails every time there is a change in the cached one.
	 */
	fun execute(tagId: String): Flow<TagDetails>
}