package neuro.cryptocurrencies.data.model.database.team

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "team_details_table"
)
data class RoomTeamMemberDetails(
	@PrimaryKey
	val id: String,
	val name: String,
	val description: String
)