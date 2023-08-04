package neuro.cryptocurrencies.domain.repository.team

import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface GetTeamMemberDetailsRepository {
	/**
	 * Get team member details.
	 * @param teamMemberId team member id.
	 * @return team member details.
	 */
	suspend fun getTeamMemberDetails(teamMemberId: String): TeamMemberDetails
}