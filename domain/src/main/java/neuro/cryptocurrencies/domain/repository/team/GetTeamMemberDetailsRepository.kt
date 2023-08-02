package neuro.cryptocurrencies.domain.repository.team

import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface GetTeamMemberDetailsRepository {
	suspend fun getTeamMemberDetails(teamMemberId: String): TeamMemberDetails
}