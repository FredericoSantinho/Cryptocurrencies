package neuro.cryptocurrencies.domain.repository.coinTicker

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface SaveCoinsTickersRepository {
	/**
	 * Save a list of coins tickers.
	 * @param coinTickers coins tickers to save.
	 */
	suspend fun saveCoinsTickers(coinTickers: List<CoinTicker>)
}