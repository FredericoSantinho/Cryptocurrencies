package neuro.cryptocurrencies.domain.repository.coin.details

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface SaveCoinDetailsWithPriceRepository {
	suspend fun saveCoinDetailsWithPrice(coinDetails: CoinDetails, price: Double)
}