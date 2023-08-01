package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.SaveTagDetailsRepository

class FetchTagDetailsUseCaseImpl(
	private val getTagDetailsRepository: GetTagDetailsRepository,
	private val saveTagDetailsRepository: SaveTagDetailsRepository
) :
	FetchTagDetailsUseCase {
	override suspend fun execute(tagId: String) {
		val tagDetails = getTagDetailsRepository.getTagDetails(tagId)
		saveTagDetailsRepository.saveTagDetails(tagDetails)
	}
}