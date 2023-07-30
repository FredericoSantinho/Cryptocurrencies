package neuro.cryptocurrencies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.mapper.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.ObserveCoinsRepository

class ObserveCoinsRepositoryImpl(
	private val coinDao: CoinDao
) : ObserveCoinsRepository {
	override fun observeCoinsTickers(): Flow<List<CoinTicker>> {
		return coinDao.observeCoinTickers().map { it.toDomain() }
	}
}