package neuro.coin.paprika.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.coin.paprika.data.dao.CoinDao
import neuro.coin.paprika.data.model.database.coin.RoomCoin

@Database(
 entities = [RoomCoin::class],
 version = 1
)
abstract class CoinpaprikaDatabase : RoomDatabase() {
 abstract val coinDao: CoinDao
} 
