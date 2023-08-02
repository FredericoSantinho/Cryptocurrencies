package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.coin.details.HasCachedCoinDetailsRepository
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
	fun `doesn'tHaveCachedCoinDetails`() = runBlocking {
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