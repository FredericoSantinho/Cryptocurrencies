package neuro.cryptocurrencies.domain.usecase.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagDetailsUseCase {
	suspend fun execute(tagId: String): TagDetails?
}