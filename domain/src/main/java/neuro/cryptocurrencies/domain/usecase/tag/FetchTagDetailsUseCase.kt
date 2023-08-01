package neuro.cryptocurrencies.domain.usecase.tag

interface FetchTagDetailsUseCase {
	suspend fun execute(tagId: String)
}