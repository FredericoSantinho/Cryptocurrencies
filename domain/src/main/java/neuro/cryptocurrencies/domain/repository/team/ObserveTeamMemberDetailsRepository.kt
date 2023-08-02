package neuro.cryptocurrencies.domain.repository.team

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface ObserveTeamMemberDetailsRepository {
	fun observeTeamMemberDetails(teamMemberId: String): Flow<TeamMemberDetails>
}