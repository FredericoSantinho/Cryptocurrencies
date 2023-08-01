package neuro.cryptocurrencies.domain.usecase.coin

interface FetchCoinsTickersUseCase {
	suspend fun fetchCoins()
}