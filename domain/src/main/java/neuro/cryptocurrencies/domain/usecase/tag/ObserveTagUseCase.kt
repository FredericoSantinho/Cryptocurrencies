package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagUseCase {
	fun execute(tagId: String): Flow<TagDetails>
}