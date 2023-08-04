package neuro.cryptocurrencies.data.repository.database.tag

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mocks.database.roomTagDetailsMock
import neuro.cryptocurrencies.domain.mocks.tagDetailsMock
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import kotlin.time.Duration

class SaveTagDetailsRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val tagDetailsDao = mock<TagDetailsDao>()

		val saveTagDetailsRepository = SaveTagDetailsRepositoryImpl(tagDetailsDao)

		verifyNoInteractions(tagDetailsDao)

		saveTagDetailsRepository.saveTagDetails(tagDetailsMock())

		verify(tagDetailsDao, times(1)).upsertTagDetails(roomTagDetailsMock())
	}
}