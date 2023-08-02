package neuro.cryptocurrencies.domain.repository.team

import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface SaveTeamMemberDetailsRepository {
	suspend fun saveTeamMemberDetails(teamMemberDetails: TeamMemberDetails)
}