package neuro.cryptocurrencies.data.repository.database.team

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedTeamMemberDetailsRepositoryImplTest {

	@Test
	fun hasHasCachedTeamMemberDetails() = runTest {
		val teamDetailsDao = mock<TeamDetailsDao>()

		val hasCachedTeamMemberDetailsRepository =
			HasCachedTeamMemberDetailsRepositoryImpl(teamDetailsDao)

		val teamMemberId = "satoshi-nakamoto"
		whenever(teamDetailsDao.hasTeamMemberDetails(teamMemberId)).thenReturn(true)

		verifyNoInteractions(teamDetailsDao)

		val hasCachedTeamMemberDetails =
			hasCachedTeamMemberDetailsRepository.hasCachedTeamMemberDetails(teamMemberId)

		verify(teamDetailsDao, times(1)).hasTeamMemberDetails(teamMemberId)

		assertTrue(hasCachedTeamMemberDetails)
	}

	@Test
	fun `doesn'tHaveHasCachedTeamMemberDetails`() = runTest {
		val teamDetailsDao = mock<TeamDetailsDao>()

		val hasCachedTeamMemberDetailsRepository =
			HasCachedTeamMemberDetailsRepositoryImpl(teamDetailsDao)

		val teamMemberId = "satoshi-nakamoto"
		whenever(teamDetailsDao.hasTeamMemberDetails(teamMemberId)).thenReturn(false)

		verifyNoInteractions(teamDetailsDao)

		val hasCachedTeamMemberDetails =
			hasCachedTeamMemberDetailsRepository.hasCachedTeamMemberDetails(teamMemberId)

		verify(teamDetailsDao, times(1)).hasTeamMemberDetails(teamMemberId)

		assertFalse(hasCachedTeamMemberDetails)
	}
}