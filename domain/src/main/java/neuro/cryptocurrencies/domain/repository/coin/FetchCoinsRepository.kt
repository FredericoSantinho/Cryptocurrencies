package neuro.cryptocurrencies.domain.repository.coin

interface FetchCoinsRepository {
	suspend fun fetchCoins()
}