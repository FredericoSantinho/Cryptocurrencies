package neuro.cryptocurrencies.data.repository.database.coin.details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.coinDetails.ObserveCoinDetailsRepository
import javax.inject.Inject

class ObserveCoinDetailsRepositoryImpl @Inject constructor(private val coinDetailsDao: CoinDetailsDao) :
	ObserveCoinDetailsRepository {
	override fun observeCoinDetails(coinId: String): Flow<CoinDetailsWithPrice> {
		return coinDetailsDao.observeCoinDetails(coinId).mapNotNull { it?.toDomain() }
	}
}