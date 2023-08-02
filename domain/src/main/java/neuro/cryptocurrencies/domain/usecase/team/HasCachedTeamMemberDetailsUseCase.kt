package neuro.cryptocurrencies.domain.usecase.team

interface HasCachedTeamMemberDetailsUseCase {
	suspend fun execute(teamMemberId: String): Boolean
}