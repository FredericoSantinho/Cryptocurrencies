package neuro.cryptocurrencies.domain.repository.coin.details

interface HasCachedCoinDetailsRepository {
	suspend fun hasCachedCoinDetails(coinId: String): Boolean
}