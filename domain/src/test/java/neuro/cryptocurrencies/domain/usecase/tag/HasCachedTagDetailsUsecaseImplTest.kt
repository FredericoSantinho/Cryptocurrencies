package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HasCachedTagDetailsUsecaseImplTest {
	@Test
	fun hasCachedTagDetailsTickers() = runTest {
		val hasCachedTagDetailsRepository = mock<HasCachedTagDetailsRepository>()

		val hasCachedTagDetailsUsecase = HasCachedTagDetailsUsecaseImpl(hasCachedTagDetailsRepository)
		val tagId = "segwit"

		whenever(hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)).thenReturn(true)

		assertTrue(hasCachedTagDetailsUsecase.execute(tagId))
	}

	@Test
	fun `doesn'tHaveCachedTagDetails`() = runTest {
		val hasCachedTagDetailsRepository = mock<HasCachedTagDetailsRepository>()

		val hasCachedTagDetailsUsecase = HasCachedTagDetailsUsecaseImpl(hasCachedTagDetailsRepository)
		val tagId = "segwit"

		whenever(hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)).thenReturn(false)

		assertFalse(hasCachedTagDetailsUsecase.execute(tagId))
	}

}