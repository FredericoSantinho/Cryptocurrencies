package neuro.cryptocurrencies.data.repository.coin.details

import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.coin.details.GetCachedCoinDetailsRepository

class GetCachedCoinDetailsRepositoryImpl(private val coinDetailsDao: CoinDetailsDao) :
	GetCachedCoinDetailsRepository {
	override suspend fun getCachedCoinDetails(coinId: String): CoinDetailsWithPrice? {
		return coinDetailsDao.getCoinDetails(coinId)?.toDomain()
	}
}