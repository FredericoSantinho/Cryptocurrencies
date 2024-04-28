package neuro.cryptocurrencies.domain.usecase.tagDetails

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository

class ObserveTagDetailsUseCaseImpl(private val observeTagDetailsRepository: ObserveTagDetailsRepository) :
	ObserveTagDetailsUseCase {
	override fun execute(tagId: String): Flow<TagDetails> {
		return observeTagDetailsRepository.observeTagDetails(tagId)
	}
}