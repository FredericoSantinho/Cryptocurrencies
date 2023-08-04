package neuro.cryptocurrencies.domain.repository.coin

interface HasCachedCoinsTickersRepository {
	/**
	 * Checks if there are cached coins tickers.
	 * @return true if there are cached coins tickers. False otherwise.
	 */
	suspend fun hasCachedCoinsTickers(): Boolean
}