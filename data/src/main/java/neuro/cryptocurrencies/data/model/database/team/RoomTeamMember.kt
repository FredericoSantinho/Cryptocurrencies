package neuro.cryptocurrencies.data.model.database.team

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "team_table"
)
data class RoomTeamMember(
	@PrimaryKey
	val id: String,
	val name: String,
	val position: String,
	val coinDetailsId: String
)