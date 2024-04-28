package neuro.cryptocurrencies.domain.usecase.coinTickers

interface HasCachedCoinsTickersUseCase {
	/**
	 * Checks if there are cached coins tickers.
	 * @return true if there are cached coins tickers. False otherwise.
	 */
	suspend fun execute(): Boolean
}