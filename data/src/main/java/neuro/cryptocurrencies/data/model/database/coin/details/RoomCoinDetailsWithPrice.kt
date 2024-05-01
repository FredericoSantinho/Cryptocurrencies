package neuro.cryptocurrencies.data.model.database.coin.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "coin_details_with_price_table"
)
data class RoomCoinDetailsWithPrice(
	@PrimaryKey
	val id: String,
	val description: String?,
	val hashAlgorithm: String,
	val logo: String?,
	val name: String?,
	val openSource: Boolean,
	val proofType: String,
	val rank: Int?,
	val symbol: String?,
	val type: String?,
	val price: Double
)