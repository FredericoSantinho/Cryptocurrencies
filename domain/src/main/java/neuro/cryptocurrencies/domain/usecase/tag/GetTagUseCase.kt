package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagUseCase {
	suspend fun execute(tagId: String): TagDetails?
}