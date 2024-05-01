package neuro.cryptocurrencies.domain.usecase.tagDetails

import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository
import javax.inject.Inject

class HasCachedTagDetailsUseCaseImpl @Inject constructor(private val hasCachedTagDetailsRepository: HasCachedTagDetailsRepository) :
	HasCachedTagDetailsUseCase {
	override suspend fun execute(tagId: String): Boolean {
		return hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)
	}
}