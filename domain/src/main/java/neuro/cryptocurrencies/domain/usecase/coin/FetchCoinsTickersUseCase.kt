package neuro.cryptocurrencies.domain.usecase.coin

interface FetchCoinsTickersUseCase {
	/**
	 * Get last coins tickers and cache them.
	 */
	suspend fun execute()
}