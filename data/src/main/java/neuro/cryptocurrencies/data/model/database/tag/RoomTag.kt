package neuro.cryptocurrencies.data.model.database.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "tag_table"
)
data class RoomTag(
	@PrimaryKey
	val id: String,
	val name: String,
	val coinDetailsId: String
)