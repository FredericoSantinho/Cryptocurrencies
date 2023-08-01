package neuro.cryptocurrencies.data.repository.coin

import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository

class HasCachedCoinsTickersRepositoryImpl(private val coinDao: CoinDao) :
	HasCachedCoinsTickersRepository {
	override suspend fun hasCachedCoinsTickers(): Boolean {
		return coinDao.hasCoinTickers()
	}
}