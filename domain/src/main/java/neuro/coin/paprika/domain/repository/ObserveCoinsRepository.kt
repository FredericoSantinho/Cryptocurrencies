package neuro.coin.paprika.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.coin.paprika.domain.entity.Coin

interface ObserveCoinsRepository {
	fun observeCoins(): Flow<List<Coin>>
}