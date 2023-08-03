package neuro.cryptocurrencies.data.repository.database.tag

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedTagDetailsRepositoryImplTest {

	@Test
	fun hasCachedTagDetails() = runTest {
		val tagDetailsDao = mock<TagDetailsDao>()

		val hasCachedTagDetailsRepository = HasCachedTagDetailsRepositoryImpl(tagDetailsDao)

		val tagId = "segwit"
		whenever(tagDetailsDao.hasTagDetails(tagId)).thenReturn(true)

		verifyNoInteractions(tagDetailsDao)

		val hasCachedTagDetails = hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)

		verify(tagDetailsDao, times(1)).hasTagDetails(tagId)

		assertTrue(hasCachedTagDetails)
	}

	@Test
	fun `doesn'tHaveCachedTagDetails`() = runTest {
		val tagDetailsDao = mock<TagDetailsDao>()

		val hasCachedTagDetailsRepository = HasCachedTagDetailsRepositoryImpl(tagDetailsDao)

		val tagId = "segwit"
		whenever(tagDetailsDao.hasTagDetails(tagId)).thenReturn(false)

		verifyNoInteractions(tagDetailsDao)

		val hasCachedTagDetails = hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)

		verify(tagDetailsDao, times(1)).hasTagDetails(tagId)

		assertFalse(hasCachedTagDetails)
	}
}