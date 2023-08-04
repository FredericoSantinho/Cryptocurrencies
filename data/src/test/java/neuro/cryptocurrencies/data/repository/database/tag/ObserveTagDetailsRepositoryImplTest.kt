package neuro.cryptocurrencies.data.repository.database.tag

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mocks.database.roomTagDetailsMock
import neuro.cryptocurrencies.domain.mocks.tagDetailsMock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.time.Duration

class ObserveTagDetailsRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val tagDetailsDao = mock<TagDetailsDao>()

		val observeTagDetailsRepository = ObserveTagDetailsRepositoryImpl(tagDetailsDao)

		val tagId = "segwit"
		whenever(tagDetailsDao.observeTagDetails(tagId)).thenReturn(flow {
			emit(
				roomTagDetailsMock()
			)
		})

		verifyNoInteractions(tagDetailsDao)

		val coinTickerList = observeTagDetailsRepository.observeTagDetails(tagId).first()

		verify(tagDetailsDao, times(1)).observeTagDetails(tagId)

		assertEquals(tagDetailsMock(), coinTickerList)
	}
}