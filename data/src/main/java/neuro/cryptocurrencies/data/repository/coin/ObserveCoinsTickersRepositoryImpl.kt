package neuro.cryptocurrencies.data.repository.coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository

class ObserveCoinsTickersRepositoryImpl(
	private val coinDao: CoinDao
) : ObserveCoinsTickersRepository {
	override fun observeCoinsTickers(): Flow<List<CoinTicker>> {
		return coinDao.observeCoinTickers().map { it.toDomain() }.distinctUntilChanged()
	}
}