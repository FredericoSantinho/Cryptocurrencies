package neuro.cryptocurrencies.domain.repository.team

interface HasCachedTeamMemberDetailsRepository {
	/**
	 * Checks if there are cached team member details.
	 * @param teamMemberId team member id.
	 * @return true if there are cached team member details. False otherwise.
	 */
	suspend fun hasCachedTeamMemberDetails(teamMemberId: String): Boolean
}