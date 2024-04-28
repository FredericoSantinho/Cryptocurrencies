package neuro.cryptocurrencies.domain.usecase.tagDetails

import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository

class HasCachedTagDetailsUseCaseImpl(private val hasCachedTagDetailsRepository: HasCachedTagDetailsRepository) :
	HasCachedTagDetailsUseCase {
	override suspend fun execute(tagId: String): Boolean {
		return hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)
	}
}