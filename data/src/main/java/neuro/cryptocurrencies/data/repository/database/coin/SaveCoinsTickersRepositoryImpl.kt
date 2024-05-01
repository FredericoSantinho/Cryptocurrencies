package neuro.cryptocurrencies.data.repository.database.coin

import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinsTickersRepository
import javax.inject.Inject

class SaveCoinsTickersRepositoryImpl @Inject constructor(private val coinTickerDao: CoinTickerDao) :
	SaveCoinsTickersRepository {
	override suspend fun saveCoinsTickers(coinTickers: List<CoinTicker>) {
		coinTickerDao.upsertCoinTickers(coinTickers.map { it.toDatabase() })
	}
}