package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagUseCase {
	suspend fun execute(tagId: String): TagDetails?
}