package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.team.RoomTeamMember
import neuro.cryptocurrencies.domain.entity.Team

fun RoomTeamMember.toDomain() = Team(id, name, position)

fun Team.toDatabase(coinDetailsId: String) = RoomTeamMember(id, name, position, coinDetailsId)