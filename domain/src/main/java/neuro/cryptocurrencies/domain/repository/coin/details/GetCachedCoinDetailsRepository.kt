package neuro.cryptocurrencies.domain.repository.coin.details

import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface GetCachedCoinDetailsRepository {
	suspend fun getCachedCoinDetails(coinId: String): CoinDetailsWithPrice?
}