package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import neuro.cryptocurrencies.data.model.database.tag.RoomTag

@Dao
interface TagDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertTags(roomTags: List<RoomTag>)
}