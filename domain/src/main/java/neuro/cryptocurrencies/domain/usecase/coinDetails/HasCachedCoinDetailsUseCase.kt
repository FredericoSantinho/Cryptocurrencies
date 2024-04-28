package neuro.cryptocurrencies.domain.usecase.coinDetails

interface HasCachedCoinDetailsUseCase {
	/**
	 * Checks if there are cached coin details.
	 * @return true if there are cached coin details. False otherwise.
	 */
	suspend fun execute(coinId: String): Boolean
}