package neuro.coin.paprika.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.coin.paprika.data.model.database.coin.RoomCoin

@Dao
interface CoinDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertContact(roomCoins: List<RoomCoin>)

	@Query("select * from coin_table order by rank asc")
	fun observeCoins(): Flow<List<RoomCoin>>
} 