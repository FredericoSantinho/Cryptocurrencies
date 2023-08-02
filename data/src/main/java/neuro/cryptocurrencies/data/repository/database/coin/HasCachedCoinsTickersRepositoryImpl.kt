package neuro.cryptocurrencies.data.repository.database.coin

import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository

class HasCachedCoinsTickersRepositoryImpl(private val coinTickerDao: CoinTickerDao) :
	HasCachedCoinsTickersRepository {
	override suspend fun hasCachedCoinsTickers(): Boolean {
		return coinTickerDao.hasCoinTickers()
	}
}