package neuro.cryptocurrencies.data.model.database.coin.details

import androidx.room.Embedded
import androidx.room.Relation
import neuro.cryptocurrencies.data.model.database.tag.RoomTag
import neuro.cryptocurrencies.data.model.database.team.RoomTeamMember

data class RoomCoinDetailsWithPriceWithTagsAndTeam(
	@Embedded val roomCoinDetailsWithPrice: RoomCoinDetailsWithPrice,
	@Relation(
		entity = RoomTag::class,
		parentColumn = "id",
		entityColumn = "coinDetailsId"
	)
	val tags: List<RoomTag>,
	@Relation(
		entity = RoomTeamMember::class,
		parentColumn = "id",
		entityColumn = "coinDetailsId"
	)
	val team: List<RoomTeamMember>
)