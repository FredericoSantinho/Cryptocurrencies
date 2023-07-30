package neuro.cryptocurrencies.domain.repository

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface GetCoinByIdRepository {
	suspend fun getCoinById(coinId: String): CoinDetails
}