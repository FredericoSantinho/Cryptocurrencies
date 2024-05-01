package neuro.cryptocurrencies.data.repository.database.coin.details

import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.domain.repository.coinDetails.HasCachedCoinDetailsRepository
import javax.inject.Inject

class HasCachedCoinDetailsRepositoryImpl @Inject constructor(private val coinDetailsDao: CoinDetailsDao) :
	HasCachedCoinDetailsRepository {
	override suspend fun hasCachedCoinDetails(coinId: String): Boolean {
		return coinDetailsDao.hasCoinDetails(coinId)
	}
}