package neuro.cryptocurrencies.data.repository.database.coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository

class ObserveCoinsTickersRepositoryImpl(
	private val coinTickerDao: CoinTickerDao
) : ObserveCoinsTickersRepository {
	override fun observeCoinsTickers(): Flow<List<CoinTicker>> {
		return coinTickerDao.observeCoinTickers().map { it.toDomain() }
	}
}