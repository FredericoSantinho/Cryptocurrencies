package neuro.cryptocurrencies.domain.usecase.team

import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.domain.repository.team.HasCachedTeamMemberDetailsRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedTeamMemberDetailsUseCaseImplTest {
	@Test
	fun hasCachedTeamMemberDetails() = runBlocking {
		val hasCachedTeamMemberDetailsRepository = mock<HasCachedTeamMemberDetailsRepository>()

		val hasCachedTeamMemberDetailsUseCase =
			HasCachedTeamMemberDetailsUseCaseImpl(hasCachedTeamMemberDetailsRepository)

		val teamMemberId = "satoshi-nakamoto"
		whenever(hasCachedTeamMemberDetailsRepository.hasCachedTeamMemberDetails(teamMemberId)).thenReturn(
			true
		)

		verifyNoInteractions(hasCachedTeamMemberDetailsRepository)

		val hasCachedTeamMemberDetails = hasCachedTeamMemberDetailsUseCase.execute(teamMemberId)

		verify(hasCachedTeamMemberDetailsRepository, times(1)).hasCachedTeamMemberDetails(teamMemberId)

		assertTrue(hasCachedTeamMemberDetails)
	}

	@Test
	fun `doesn'tHaveCachedTeamMemberDetails`() = runBlocking {
		val hasCachedTeamMemberDetailsRepository = mock<HasCachedTeamMemberDetailsRepository>()

		val hasCachedTeamMemberDetailsUseCase =
			HasCachedTeamMemberDetailsUseCaseImpl(hasCachedTeamMemberDetailsRepository)

		val teamMemberId = "satoshi-nakamoto"
		whenever(hasCachedTeamMemberDetailsRepository.hasCachedTeamMemberDetails(teamMemberId)).thenReturn(
			false
		)

		verifyNoInteractions(hasCachedTeamMemberDetailsRepository)

		val hasCachedTeamMemberDetails = hasCachedTeamMemberDetailsUseCase.execute(teamMemberId)

		verify(hasCachedTeamMemberDetailsRepository, times(1)).hasCachedTeamMemberDetails(teamMemberId)

		assertFalse(hasCachedTeamMemberDetails)
	}
}