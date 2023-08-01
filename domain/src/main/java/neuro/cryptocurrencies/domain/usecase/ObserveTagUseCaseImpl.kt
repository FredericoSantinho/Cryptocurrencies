package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.ObserveTagRepository

class ObserveTagUseCaseImpl(private val observeTagRepository: ObserveTagRepository) :
	ObserveTagUseCase {
	override fun execute(tagId: String): Flow<TagDetails> {
		return observeTagRepository.observeTag(tagId)
	}
}