package neuro.cryptocurrencies.domain.repository.coinTicker

interface HasCachedCoinsTickersRepository {
	/**
	 * Checks if there are cached coins tickers.
	 * @return true if there are cached coins tickers. False otherwise.
	 */
	suspend fun hasCachedCoinsTickers(): Boolean
}