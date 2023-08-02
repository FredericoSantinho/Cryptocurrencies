package neuro.cryptocurrencies.data.repository.database.coin

import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinTickersRepository

class SaveCoinTickersRepositoryImpl(private val coinTickerDao: CoinTickerDao) :
	SaveCoinTickersRepository {
	override suspend fun saveCoinTickers(coinTickers: List<CoinTicker>) {
		coinTickerDao.upsertCoinTickers(coinTickers.map { it.toDatabase() })
	}
}