package neuro.cryptocurrencies.domain.usecase.team

interface FetchTeamMemberUseCase {
	suspend fun execute(teamMemberId: String)
}