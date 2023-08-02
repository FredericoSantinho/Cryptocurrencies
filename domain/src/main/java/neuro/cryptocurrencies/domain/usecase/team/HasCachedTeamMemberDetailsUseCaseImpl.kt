package neuro.cryptocurrencies.domain.usecase.team

import neuro.cryptocurrencies.domain.repository.team.HasCachedTeamMemberDetailsRepository

class HasCachedTeamMemberDetailsUseCaseImpl(private val hasCachedTeamMemberDetailsRepository: HasCachedTeamMemberDetailsRepository) :
	HasCachedTeamMemberDetailsUseCase {
	override suspend fun execute(teamMemberId: String): Boolean {
		return hasCachedTeamMemberDetailsRepository.hasCachedTeamMemberDetails(teamMemberId)
	}
}