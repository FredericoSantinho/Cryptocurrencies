package neuro.cryptocurrencies.data.repository.database.team

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.data.mocks.database.roomTeamMemberDetailsMock
import neuro.cryptocurrencies.domain.mocks.teamMemberDetailsMock
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import kotlin.time.Duration

class SaveTeamMemberDetailsRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val teamDetailsDao = mock<TeamDetailsDao>()

		val saveTeamMemberDetailsRepository = SaveTeamMemberDetailsRepositoryImpl(teamDetailsDao)

		verifyNoInteractions(teamDetailsDao)

		saveTeamMemberDetailsRepository.saveTeamMemberDetails(teamMemberDetailsMock())

		verify(teamDetailsDao, times(1)).upsertTeamMemberDetails(roomTeamMemberDetailsMock())
	}
}