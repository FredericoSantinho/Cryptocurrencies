package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.team.TeamMemberDto
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

fun TeamMemberDto.toDomain() = TeamMemberDetails(id, name, description ?: "")