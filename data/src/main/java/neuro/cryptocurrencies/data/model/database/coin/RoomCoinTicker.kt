package neuro.cryptocurrencies.data.model.database.coin

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	tableName = "coin_ticker_table",
	indices = [Index(value = ["rank"], unique = true)]
)
data class RoomCoinTicker(
	@PrimaryKey
	val id: String,
	val name: String?,
	val rank: Int?,
	val symbol: String?,
	val price: Double
)