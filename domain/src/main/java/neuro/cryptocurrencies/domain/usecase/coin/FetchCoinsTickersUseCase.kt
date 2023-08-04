package neuro.cryptocurrencies.domain.usecase.coin

interface FetchCoinsTickersUseCase {
	/**
	 * Get most recent coins tickers and cache them.
	 */
	suspend fun execute()
}