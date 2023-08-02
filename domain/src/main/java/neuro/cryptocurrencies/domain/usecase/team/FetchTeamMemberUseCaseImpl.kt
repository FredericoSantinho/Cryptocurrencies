package neuro.cryptocurrencies.domain.usecase.team

import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.SaveTeamMemberDetailsRepository

class FetchTeamMemberUseCaseImpl(
	private val getTeamMemberDetailsRepository: GetTeamMemberDetailsRepository,
	private val saveTeamMemberDetailsRepository: SaveTeamMemberDetailsRepository
) : FetchTeamMemberUseCase {
	override suspend fun execute(teamMemberId: String) {
		val teamMemberDetails = getTeamMemberDetailsRepository.getTeamMemberDetails(teamMemberId)
		saveTeamMemberDetailsRepository.saveTeamMemberDetails(teamMemberDetails)
	}
}