package neuro.cryptocurrencies.domain.repository.team

import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface SaveTeamMemberDetailsRepository {
	/**
	 * Save team member details.
	 * @param teamMemberDetails team member details to save.
	 */
	suspend fun saveTeamMemberDetails(teamMemberDetails: TeamMemberDetails)
}