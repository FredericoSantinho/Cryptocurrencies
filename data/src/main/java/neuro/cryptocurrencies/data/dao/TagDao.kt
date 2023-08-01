package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails

@Dao
interface TagDao {

	@Query("select * from tag_table where id=:tagId")
	fun observeTag(tagId: String): Flow<RoomTagDetails?>

	@Query("select * from tag_table where id=:tagId")
	fun getTag(tagId: String): RoomTagDetails?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun upsertTag(roomTagDetails: RoomTagDetails)
} 