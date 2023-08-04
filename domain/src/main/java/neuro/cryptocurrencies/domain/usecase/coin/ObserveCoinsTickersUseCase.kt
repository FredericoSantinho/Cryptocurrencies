package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsTickersUseCase {
	/**
	 * Observe cached coins tickers.
	 * @return a Flow that will emit a list of coin tickers every time there is a change in cached ones.
	 */
	fun execute(): Flow<List<CoinTicker>>
}