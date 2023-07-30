package neuro.cryptocurrencies.domain.repository

interface FetchCoinsRepository {
	suspend fun fetchCoins()
}