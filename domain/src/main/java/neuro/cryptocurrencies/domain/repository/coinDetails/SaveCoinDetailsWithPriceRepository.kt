package neuro.cryptocurrencies.domain.repository.coinDetails

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface SaveCoinDetailsWithPriceRepository {
	/**
	 * Save coin details with price.
	 * @param coinDetails coin details to save.
	 * @param price price.
	 */
	suspend fun saveCoinDetailsWithPrice(coinDetails: CoinDetails, price: Double)
}