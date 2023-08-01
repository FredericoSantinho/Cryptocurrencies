package neuro.cryptocurrencies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails

@Database(
 entities = [RoomCoinTicker::class, RoomTagDetails::class],
 version = 1
)
abstract class CryptocurrenciesDatabase : RoomDatabase() {
 abstract val coinDao: CoinDao
 abstract val tagDao: TagDao
}
