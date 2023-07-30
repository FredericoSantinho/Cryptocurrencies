package neuro.cryptocurrencies.domain.usecase

interface FetchCoinsUseCase {
	suspend fun fetchCoins()
}