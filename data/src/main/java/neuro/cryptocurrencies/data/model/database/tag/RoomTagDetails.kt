package neuro.cryptocurrencies.data.model.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "tag_details_table"
)
data class RoomTagDetails(
	@PrimaryKey
	val id: String,
	val name: String,
	val description: String,
)