package neuro.cryptocurrencies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.model.database.coin.RoomCoin

@Database(
 entities = [RoomCoin::class],
 version = 1
)
abstract class CryptocurrenciesDatabase : RoomDatabase() {
 abstract val coinDao: CoinDao
} 
