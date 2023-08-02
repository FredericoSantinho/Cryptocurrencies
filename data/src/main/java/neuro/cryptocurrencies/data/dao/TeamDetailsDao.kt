package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.team.RoomTeamMemberDetails

@Dao
interface TeamDetailsDao {

	@Query("SELECT * FROM team_details_table WHERE id=:teamMemberId")
	fun observeTeamMemberDetails(teamMemberId: String): Flow<RoomTeamMemberDetails?>

	@Query("SELECT COUNT(1) FROM team_details_table WHERE id =:teamMemberId")
	suspend fun hasTeamMemberDetails(teamMemberId: String): Boolean

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertTeamMemberDetails(roomTeamMemberDetails: RoomTeamMemberDetails)
}