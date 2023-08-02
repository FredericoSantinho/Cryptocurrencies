package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class HasCachedCoinsTickersUseCaseImplTest {
	@Test
	fun hasCachedCoinTickers() = runTest {
		val hasCachedCoinsTickersRepository = mock<HasCachedCoinsTickersRepository>()

		val hasCachedCoinsTickersUseCase =
			HasCachedCoinsTickersUseCaseImpl(hasCachedCoinsTickersRepository)

		whenever(hasCachedCoinsTickersRepository.hasCachedCoinsTickers()).thenReturn(true)

		verifyNoInteractions(hasCachedCoinsTickersRepository)

		assertTrue(hasCachedCoinsTickersUseCase.execute())

		verify(hasCachedCoinsTickersRepository, times(1)).hasCachedCoinsTickers()
	}

	@Test
	fun `don'tHasCachedCoinTickers`() = runTest {
		val hasCachedCoinsTickersRepository = mock<HasCachedCoinsTickersRepository>()

		val hasCachedCoinsTickersUseCase =
			HasCachedCoinsTickersUseCaseImpl(hasCachedCoinsTickersRepository)

		whenever(hasCachedCoinsTickersRepository.hasCachedCoinsTickers()).thenReturn(false)

		verifyNoInteractions(hasCachedCoinsTickersRepository)

		assertFalse(hasCachedCoinsTickersUseCase.execute())

		verify(hasCachedCoinsTickersRepository, times(1)).hasCachedCoinsTickers()
	}
}