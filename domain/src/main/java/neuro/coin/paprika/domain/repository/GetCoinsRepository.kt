package neuro.coin.paprika.domain.repository

import neuro.coin.paprika.domain.entity.Coin

interface GetCoinsRepository {
	suspend fun getCoins(): List<Coin>
}