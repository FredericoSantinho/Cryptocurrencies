package neuro.cryptocurrencies.domain.repository.coin

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface GetCoinTickerRepository {
	suspend fun getCoinTicker(coinId: String): CoinTicker
}