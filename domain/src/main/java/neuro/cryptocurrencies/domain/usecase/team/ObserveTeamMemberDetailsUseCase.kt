package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails

interface ObserveTeamMemberDetailsUseCase {
	/**
	 * Observe cached team member details.
	 * @param teamMemberId team member id.
	 * @return a Flow that will emit team member details every time there is a change in cache.
	 */
	fun execute(teamMemberId: String): Flow<TeamMemberDetails>
}