package neuro.coin.paprika.domain.repository

import neuro.coin.paprika.domain.entity.CoinDetails

interface GetCoinByIdRepository {
	suspend fun getCoinById(coinId: String): CoinDetails
}