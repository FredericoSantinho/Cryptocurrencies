package neuro.cryptocurrencies.data.repository.coin.details

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.dao.TeamDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.coin.details.SaveCoinDetailsWithPriceRepository

class SaveCoinDetailsWithPriceRepositoryImpl(
	private val database: RoomDatabase,
	private val coinDetailsDao: CoinDetailsDao,
	private val tagDao: TagDao,
	private val teamDao: TeamDao
) : SaveCoinDetailsWithPriceRepository {
	override suspend fun saveCoinDetailsWithPrice(coinDetails: CoinDetails, price: Double) {
		database.withTransaction {
			tagDao.upsertTags(coinDetails.tags.map { it.toDatabase(coinDetails.id) })
			teamDao.upsertTeamMember(coinDetails.team.map { it.toDatabase(coinDetails.id) })
			coinDetailsDao.upsertCoinDetails(coinDetails.toDatabase(price))
		}
	}
}