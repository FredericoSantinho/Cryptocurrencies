package neuro.cryptocurrencies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.dao.TeamDao
import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPrice
import neuro.cryptocurrencies.data.model.database.tag.RoomTag
import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails
import neuro.cryptocurrencies.data.model.database.team.RoomTeamMember
import neuro.cryptocurrencies.data.model.database.team.RoomTeamMemberDetails

@Database(
	entities = [RoomCoinTicker::class, RoomCoinDetailsWithPrice::class, RoomTagDetails::class, RoomTag::class, RoomTeamMember::class, RoomTeamMemberDetails::class],
	version = 1
)
abstract class CryptocurrenciesDatabase : RoomDatabase() {
	abstract val coinTickerDao: CoinTickerDao
	abstract val coinDetailsDao: CoinDetailsDao
	abstract val tagDetailsDao: TagDetailsDao
	abstract val tagDao: TagDao
	abstract val teamDao: TeamDao
	abstract val teamDetailsDao: TeamDetailsDao
}
