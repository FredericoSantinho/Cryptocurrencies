package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.repository.FetchTagRepository

class FetchTagUseCaseImpl(private val fetchTagRepository: FetchTagRepository) :
	FetchTagUseCase {
	override suspend fun execute(tagId: String) {
		fetchTagRepository.fetchTag(tagId)
	}
}