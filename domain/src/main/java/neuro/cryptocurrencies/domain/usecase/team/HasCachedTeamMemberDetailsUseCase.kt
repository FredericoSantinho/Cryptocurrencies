package neuro.cryptocurrencies.domain.usecase.team

interface HasCachedTeamMemberDetailsUseCase {
	/**
	 * Checks if there are cached team member details.
	 * @param teamMemberId team member id.
	 * @return true if there are cached team member details. False otherwise.
	 */
	suspend fun execute(teamMemberId: String): Boolean
}