package neuro.coin.paprika.domain.repository

import neuro.coin.paprika.domain.model.CoinDetails

interface GetCoinRepository {
	suspend fun getCoinById(coinId: String): CoinDetails
}