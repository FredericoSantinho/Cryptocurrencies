package neuro.cryptocurrencies.domain.repository.coinDetails

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface GetCoinDetailsRepository {
	/**
	 * Get coin details.
	 * @param coinId coin id.
	 * @return coin details.
	 */
	suspend fun getCoinDetails(coinId: String): CoinDetails
}