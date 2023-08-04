package neuro.cryptocurrencies.domain.usecase.team

import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.SaveTeamMemberDetailsRepository

class FetchTeamMemberDetailsUseCaseImpl(
	private val getTeamMemberDetailsRepository: GetTeamMemberDetailsRepository,
	private val saveTeamMemberDetailsRepository: SaveTeamMemberDetailsRepository
) : FetchTeamMemberDetailsUseCase {
	override suspend fun execute(teamMemberId: String) {
		val teamMemberDetails = getTeamMemberDetailsRepository.getTeamMemberDetails(teamMemberId)
		saveTeamMemberDetailsRepository.saveTeamMemberDetails(teamMemberDetails)
	}
}