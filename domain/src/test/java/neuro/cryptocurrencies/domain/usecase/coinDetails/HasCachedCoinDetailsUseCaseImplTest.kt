package neuro.cryptocurrencies.domain.usecase.coinDetails

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.coinDetails.HasCachedCoinDetailsRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedCoinDetailsUseCaseImplTest {

	@Test
	fun hasCachedCoinDetails() = runTest {
		val hasCachedCoinDetailsRepository = mock<HasCachedCoinDetailsRepository>()

		val hasCachedCoinDetailsUseCase =
			HasCachedCoinDetailsUseCaseImpl(hasCachedCoinDetailsRepository)

		val coinId = "btc-bitcoin"
		whenever(hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)).thenReturn(true)

		verifyNoInteractions(hasCachedCoinDetailsRepository)

		val hasCachedCoinDetails = hasCachedCoinDetailsUseCase.execute(coinId)

		verify(hasCachedCoinDetailsRepository, times(1)).hasCachedCoinDetails(coinId)

		assertTrue(hasCachedCoinDetails)
	}

	@Test
	fun `doesn'tHaveCachedCoinDetails`() = runTest {
		val hasCachedCoinDetailsRepository = mock<HasCachedCoinDetailsRepository>()

		val hasCachedCoinDetailsUseCase =
			HasCachedCoinDetailsUseCaseImpl(hasCachedCoinDetailsRepository)

		val coinId = "btc-bitcoin"
		whenever(hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)).thenReturn(false)

		verifyNoInteractions(hasCachedCoinDetailsRepository)

		val hasCachedCoinDetails = hasCachedCoinDetailsUseCase.execute(coinId)

		verify(hasCachedCoinDetailsRepository, times(1)).hasCachedCoinDetails(coinId)

		assertFalse(hasCachedCoinDetails)
	}
}