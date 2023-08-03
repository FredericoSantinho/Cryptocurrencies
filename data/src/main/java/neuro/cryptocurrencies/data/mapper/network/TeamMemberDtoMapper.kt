package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.team.TeamMemberDetailsDto
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

fun TeamMemberDetailsDto.toDomain() = TeamMemberDetails(id, name, description ?: "")