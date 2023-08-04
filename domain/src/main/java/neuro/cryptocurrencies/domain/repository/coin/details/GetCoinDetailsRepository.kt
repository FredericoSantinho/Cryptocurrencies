package neuro.cryptocurrencies.domain.repository.coin.details

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface GetCoinDetailsRepository {
	/**
	 * Get coin details.
	 * @param coinId coin id.
	 * @return coin details.
	 */
	suspend fun getCoinDetails(coinId: String): CoinDetails
}