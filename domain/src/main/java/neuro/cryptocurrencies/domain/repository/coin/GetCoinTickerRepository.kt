package neuro.cryptocurrencies.domain.repository.coin

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface GetCoinTickerRepository {
	/**
	 * Get coin ticker.
	 * @param coinId coin id.
	 * @return coin ticker.
	 */
	suspend fun getCoinTicker(coinId: String): CoinTicker
}