package neuro.cryptocurrencies.domain.usecase.coinTickers

interface FetchCoinsTickersUseCase {
	/**
	 * Get most recent coins tickers and cache them.
	 */
	suspend fun execute()
}