package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails

@Dao
interface TagDetailsDao {

	@Query("select * from tag_details_table where id=:tagId")
	fun observeTagDetails(tagId: String): Flow<RoomTagDetails?>

	@Query("select * from tag_details_table where id=:tagId")
	suspend fun getTagDetails(tagId: String): RoomTagDetails?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertTagDetails(roomTagDetails: RoomTagDetails)
}