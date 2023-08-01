package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository

class HasCachedTagDetailsUsecaseImpl(private val hasCachedTagDetailsRepository: HasCachedTagDetailsRepository) :
	HasCachedTagDetailsUseCase {
	override suspend fun execute(tagId: String): Boolean {
		return hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)
	}
}