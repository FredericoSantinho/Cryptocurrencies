package neuro.cryptocurrencies.domain.usecase.coin

interface FetchCoinsUseCase {
	suspend fun fetchCoins()
}