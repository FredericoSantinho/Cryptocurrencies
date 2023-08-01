package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.GetTagRepository

class GetTagUsecaseImpl(private val getTagRepository: GetTagRepository) : GetTagUseCase {
	override suspend fun execute(tagId: String): TagDetails? {
		return getTagRepository.getTag(tagId)
	}
}