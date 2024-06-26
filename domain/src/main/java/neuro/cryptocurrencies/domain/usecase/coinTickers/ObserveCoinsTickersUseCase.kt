package neuro.cryptocurrencies.domain.usecase.coinTickers

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsTickersUseCase {
	/**
	 * Observe cached coins tickers.
	 * @return a Flow that will emit a list of coins tickers every time there is a change in cache.
	 */
	fun execute(): Flow<List<CoinTicker>>
}