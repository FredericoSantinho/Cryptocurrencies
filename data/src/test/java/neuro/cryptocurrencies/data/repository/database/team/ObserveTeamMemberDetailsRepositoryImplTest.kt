package neuro.cryptocurrencies.data.repository.database.team

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.data.mocks.database.roomTeamMemberDetailsMock
import neuro.cryptocurrencies.domain.mocks.teamMemberDetailsMock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.time.Duration

class ObserveTeamMemberDetailsRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val teamDetailsDao = mock<TeamDetailsDao>()

		val observeTeamMemberDetailsRepository = ObserveTeamMemberDetailsRepositoryImpl(teamDetailsDao)

		val teamMemberId = "satoshi-nakamoto"
		whenever(teamDetailsDao.observeTeamMemberDetails(teamMemberId)).thenReturn(flow {
			emit(
				roomTeamMemberDetailsMock()
			)
		})

		verifyNoInteractions(teamDetailsDao)

		val teamMemberDetails =
			observeTeamMemberDetailsRepository.observeTeamMemberDetails(teamMemberId).first()

		verify(teamDetailsDao, times(1)).observeTeamMemberDetails(teamMemberId)

		assertEquals(teamMemberDetailsMock(), teamMemberDetails)
	}
}