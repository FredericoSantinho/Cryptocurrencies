package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.team.RoomTeamMember
import neuro.cryptocurrencies.domain.entity.TeamMember

fun RoomTeamMember.toDomain() = TeamMember(id, name, position)

fun TeamMember.toDatabase(coinDetailsId: String) = RoomTeamMember(id, name, position, coinDetailsId)