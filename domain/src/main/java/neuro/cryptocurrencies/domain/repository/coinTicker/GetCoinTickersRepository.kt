package neuro.cryptocurrencies.domain.repository.coinTicker

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface GetCoinTickersRepository {
	/**
	 * Get coins tickers.
	 * @return a list of coin tickers.
	 */
	suspend fun getCoinTickers(): List<CoinTicker>
}