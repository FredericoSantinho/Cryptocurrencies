package neuro.cryptocurrencies.domain.usecase

interface FetchTagUseCase {
	suspend fun execute(tagId: String)
}