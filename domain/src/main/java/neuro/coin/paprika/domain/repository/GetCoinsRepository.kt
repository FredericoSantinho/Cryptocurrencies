package neuro.coin.paprika.domain.repository

import neuro.coin.paprika.domain.model.Coin

interface GetCoinsRepository {
	suspend fun getCoins(): List<Coin>
}