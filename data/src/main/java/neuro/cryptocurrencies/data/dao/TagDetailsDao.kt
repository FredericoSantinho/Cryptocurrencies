package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails

@Dao
interface TagDetailsDao {

	@Query("SELECT * FROM tag_details_table WHERE id=:tagId")
	fun observeTagDetails(tagId: String): Flow<RoomTagDetails?>

	@Query("SELECT COUNT(1) FROM tag_details_table WHERE id =:tagId")
	suspend fun hasTagDetails(tagId: String): Boolean

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertTagDetails(roomTagDetails: RoomTagDetails)
}