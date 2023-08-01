package neuro.cryptocurrencies.domain.usecase.tag

interface FetchTagUseCase {
	suspend fun execute(tagId: String)
}