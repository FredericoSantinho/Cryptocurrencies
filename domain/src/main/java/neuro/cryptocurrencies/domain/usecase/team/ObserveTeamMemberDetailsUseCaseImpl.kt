package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails
import neuro.cryptocurrencies.domain.repository.team.ObserveTeamMemberDetailsRepository

class ObserveTeamMemberDetailsUseCaseImpl(private val observeTeamMemberDetailsRepository: ObserveTeamMemberDetailsRepository) :
	ObserveTeamMemberDetailsUseCase {
	override fun execute(teamMemberId: String): Flow<TeamMemberDetails> {
		return observeTeamMemberDetailsRepository.observeTeamMemberDetails(teamMemberId)
	}
}