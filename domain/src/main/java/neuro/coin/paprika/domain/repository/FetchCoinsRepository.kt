package neuro.coin.paprika.domain.repository

interface FetchCoinsRepository {
	suspend fun fetchCoins()
}