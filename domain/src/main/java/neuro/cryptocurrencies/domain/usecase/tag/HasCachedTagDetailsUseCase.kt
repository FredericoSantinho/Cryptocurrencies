package neuro.cryptocurrencies.domain.usecase.tag

interface HasCachedTagDetailsUseCase {
	suspend fun execute(tagId: String): Boolean
}