package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface ObserveTeamMemberDetailsUseCase {
	fun execute(teamMemberId: String): Flow<TeamMemberDetails>
}