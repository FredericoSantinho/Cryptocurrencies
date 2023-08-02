package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ObserveCoinsTickersUseCaseImplTest {
	@Test
	fun test() = runTest {
		val observeCoinsTickersRepository = mock<ObserveCoinsTickersRepository>()

		val observeCoinsTickersUseCase = ObserveCoinsTickersUseCaseImpl(observeCoinsTickersRepository)

		whenever(observeCoinsTickersRepository.observeCoinsTickers()).thenReturn(flow {
			emit(buildCoinTickerList())
		})

		val coinTickersFlow = observeCoinsTickersUseCase.execute().first()

		assertEquals(buildCoinTickerList(), coinTickersFlow)
	}

	private fun buildCoinTickerList(): List<CoinTicker> {
		return listOf(
			CoinTicker("1", "Bitcoin", 1, "BTC", 25241.0)
		)
	}
}