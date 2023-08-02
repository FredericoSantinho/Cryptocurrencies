package neuro.cryptocurrencies.domain.repository.team

interface HasCachedTeamMemberDetailsRepository {
	suspend fun hasCachedTeamMemberDetails(teamMemberId: String): Boolean
}