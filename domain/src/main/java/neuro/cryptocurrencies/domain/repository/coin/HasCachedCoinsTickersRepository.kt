package neuro.cryptocurrencies.domain.repository.coin

interface HasCachedCoinsTickersRepository {
	suspend fun hasCachedCoinsTickers(): Boolean
}