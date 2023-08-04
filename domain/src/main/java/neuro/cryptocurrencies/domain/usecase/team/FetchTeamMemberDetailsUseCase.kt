package neuro.cryptocurrencies.domain.usecase.team

interface FetchTeamMemberDetailsUseCase {
	/**
	 * Get most recent team member details and cache it.
	 * @param tagId team member id.
	 */
	suspend fun execute(teamMemberId: String)
}