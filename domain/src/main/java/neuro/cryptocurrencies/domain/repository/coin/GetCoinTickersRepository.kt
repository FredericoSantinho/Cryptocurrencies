package neuro.cryptocurrencies.domain.repository.coin

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface GetCoinTickersRepository {
	suspend fun getCoinTickers(): List<CoinTicker>
}