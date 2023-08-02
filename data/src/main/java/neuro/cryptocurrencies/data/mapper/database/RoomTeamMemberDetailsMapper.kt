package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.team.RoomTeamMemberDetails
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

fun RoomTeamMemberDetails.toDomain() = TeamMemberDetails(id, name, description)

fun TeamMemberDetails.toDatabase() = RoomTeamMemberDetails(id, name, description)