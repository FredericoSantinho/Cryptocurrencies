package neuro.cryptocurrencies.domain.repository

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface GetCoinDetailsRepository {
	suspend fun getCoinDetails(coinId: String): CoinDetails
}