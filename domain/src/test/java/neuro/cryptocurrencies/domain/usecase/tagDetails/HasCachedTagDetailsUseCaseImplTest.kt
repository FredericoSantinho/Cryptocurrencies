package neuro.cryptocurrencies.domain.usecase.tagDetails

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedTagDetailsUseCaseImplTest {
	@Test
	fun hasCachedTagDetailsTickers() = runTest {
		val hasCachedTagDetailsRepository = mock<HasCachedTagDetailsRepository>()

		val hasCachedTagDetailsUsecase = HasCachedTagDetailsUseCaseImpl(hasCachedTagDetailsRepository)
		val tagId = "segwit"

		whenever(hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)).thenReturn(true)

		verifyNoInteractions(hasCachedTagDetailsRepository)

		assertTrue(hasCachedTagDetailsUsecase.execute(tagId))

		verify(hasCachedTagDetailsRepository, times(1)).hasCachedTagDetails(tagId)
	}

	@Test
	fun `doesn'tHaveCachedTagDetails`() = runTest {
		val hasCachedTagDetailsRepository = mock<HasCachedTagDetailsRepository>()

		val hasCachedTagDetailsUsecase = HasCachedTagDetailsUseCaseImpl(hasCachedTagDetailsRepository)
		val tagId = "segwit"

		whenever(hasCachedTagDetailsRepository.hasCachedTagDetails(tagId)).thenReturn(false)

		verifyNoInteractions(hasCachedTagDetailsRepository)

		assertFalse(hasCachedTagDetailsUsecase.execute(tagId))

		verify(hasCachedTagDetailsRepository, times(1)).hasCachedTagDetails(tagId)
	}

}