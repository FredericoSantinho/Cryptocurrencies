package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import neuro.cryptocurrencies.data.model.database.team.RoomTeamMember

@Dao
interface TeamDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertTeamMember(roomTeamMembers: List<RoomTeamMember>)
}