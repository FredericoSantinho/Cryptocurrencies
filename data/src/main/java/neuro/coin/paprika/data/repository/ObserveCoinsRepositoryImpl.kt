package neuro.coin.paprika.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import neuro.coin.paprika.data.dao.CoinDao
import neuro.coin.paprika.data.mapper.toDomain
import neuro.coin.paprika.domain.entity.Coin
import neuro.coin.paprika.domain.repository.ObserveCoinsRepository

class ObserveCoinsRepositoryImpl(
	private val coinDao: CoinDao
) : ObserveCoinsRepository {
	override fun observeCoins(): Flow<List<Coin>> {
		return coinDao.observeCoins().distinctUntilChanged().map { it.toDomain() }
	}
}