package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker

@Dao
interface CoinTickerDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertCoinTickers(roomCoinTickers: List<RoomCoinTicker>)

	@Query("select * from coin_ticker_table order by rank asc")
	fun observeCoinTickers(): Flow<List<RoomCoinTicker>>

	@Query("SELECT COUNT(1) FROM coin_ticker_table")
	suspend fun hasCoinTickers(): Boolean
} 