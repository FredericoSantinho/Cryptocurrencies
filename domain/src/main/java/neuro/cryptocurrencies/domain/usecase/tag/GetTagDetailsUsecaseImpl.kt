package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository

class GetTagDetailsUsecaseImpl(private val getTagDetailsRepository: GetTagDetailsRepository) :
	GetTagDetailsUseCase {
	override suspend fun execute(tagId: String): TagDetails? {
		return getTagDetailsRepository.getTagDetails(tagId)
	}
}