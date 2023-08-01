package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.repository.tag.FetchTagRepository

class FetchTagUseCaseImpl(private val fetchTagRepository: FetchTagRepository) :
	FetchTagUseCase {
	override suspend fun execute(tagId: String) {
		fetchTagRepository.fetchTag(tagId)
	}
}