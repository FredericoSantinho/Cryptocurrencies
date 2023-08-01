package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails

interface ObserveTagDetailsUseCase {
	fun execute(tagId: String): Flow<TagDetails>
}