package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.repository.tag.FetchTagDetailsRepository

class FetchTagDetailsUseCaseImpl(private val fetchTagDetailsRepository: FetchTagDetailsRepository) :
	FetchTagDetailsUseCase {
	override suspend fun execute(tagId: String) {
		fetchTagDetailsRepository.fetchTagDetails(tagId)
	}
}