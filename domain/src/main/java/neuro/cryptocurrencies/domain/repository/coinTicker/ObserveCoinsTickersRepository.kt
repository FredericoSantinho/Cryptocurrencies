package neuro.cryptocurrencies.domain.repository.coinTicker

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsTickersRepository {
	/**
	 * Observe cached coins tickers.
	 * @return a Flow that will emit a list of coins tickers every time there is a change in cache.
	 */
	fun observeCoinsTickers(): Flow<List<CoinTicker>>
}