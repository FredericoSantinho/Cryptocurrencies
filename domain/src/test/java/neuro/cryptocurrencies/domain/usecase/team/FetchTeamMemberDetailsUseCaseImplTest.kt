package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.mocks.teamMemberDetailsMock
import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.SaveTeamMemberDetailsRepository
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class FetchTeamMemberDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val getTeamMemberDetailsRepository = mock<GetTeamMemberDetailsRepository>()
		val saveTeamMemberDetailsRepository = mock<SaveTeamMemberDetailsRepository>()

		val fetchTeamMemberUseCase =
			FetchTeamMemberDetailsUseCaseImpl(
				getTeamMemberDetailsRepository,
				saveTeamMemberDetailsRepository
			)

		val teamMemberId = "satoshi-nakamoto"
		val teamMemberDetails = teamMemberDetailsMock()
		whenever(getTeamMemberDetailsRepository.getTeamMemberDetails(teamMemberId)).thenReturn(
			teamMemberDetails
		)

		verifyNoInteractions(getTeamMemberDetailsRepository)
		verifyNoInteractions(saveTeamMemberDetailsRepository)

		fetchTeamMemberUseCase.execute(teamMemberId)

		verify(getTeamMemberDetailsRepository, times(1)).getTeamMemberDetails(teamMemberId)
		verify(saveTeamMemberDetailsRepository, times(1)).saveTeamMemberDetails(teamMemberDetails)
	}
}