package neuro.cryptocurrencies.domain.repository.coin

import neuro.cryptocurrencies.domain.entity.CoinTicker

interface SaveCoinTickersRepository {
	suspend fun saveCoinTickers(coinTickers: List<CoinTicker>)
}