package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.mocks.teamMemberDetailsMock
import neuro.cryptocurrencies.domain.repository.team.ObserveTeamMemberDetailsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class ObserveTeamMemberDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val observeTeamMemberDetailsRepository = mock<ObserveTeamMemberDetailsRepository>()

		val observeTeamMemberDetailsUseCase =
			ObserveTeamMemberDetailsUseCaseImpl(observeTeamMemberDetailsRepository)

		val teamMemberId = "satoshi-nakamoto"
		whenever(observeTeamMemberDetailsRepository.observeTeamMemberDetails(teamMemberId)).thenReturn(
			flow { emit(teamMemberDetailsMock()) }
		)

		verifyNoInteractions(observeTeamMemberDetailsRepository)

		val teamMemberDetails = observeTeamMemberDetailsUseCase.execute(teamMemberId).first()

		verify(observeTeamMemberDetailsRepository, times(1)).observeTeamMemberDetails(teamMemberId)

		assertEquals(teamMemberDetailsMock(), teamMemberDetails)
	}
}