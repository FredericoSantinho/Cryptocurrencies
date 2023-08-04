package neuro.cryptocurrencies.domain.repository.coin.details

interface HasCachedCoinDetailsRepository {
	/**
	 * Checks if there are cached coin details.
	 * @return true if there are cached coin details. False otherwise.
	 */
	suspend fun hasCachedCoinDetails(coinId: String): Boolean
}