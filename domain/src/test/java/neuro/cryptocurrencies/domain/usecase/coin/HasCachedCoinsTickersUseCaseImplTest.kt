package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HasCachedCoinsTickersUseCaseImplTest {
	@Test
	fun hasCachedCoinTickers() = runTest {
		val hasCachedCoinsTickersRepository = mock<HasCachedCoinsTickersRepository>()

		val hasCachedCoinsTickersUseCase =
			HasCachedCoinsTickersUseCaseImpl(hasCachedCoinsTickersRepository)

		whenever(hasCachedCoinsTickersRepository.hasCachedCoinsTickers()).thenReturn(true)

		assertTrue(hasCachedCoinsTickersUseCase.execute())
	}

	@Test
	fun `don'tHasCachedCoinTickers`() = runTest {
		val hasCachedCoinsTickersRepository = mock<HasCachedCoinsTickersRepository>()

		val hasCachedCoinsTickersUseCase =
			HasCachedCoinsTickersUseCaseImpl(hasCachedCoinsTickersRepository)

		whenever(hasCachedCoinsTickersRepository.hasCachedCoinsTickers()).thenReturn(false)

		assertFalse(hasCachedCoinsTickersUseCase.execute())
	}
}