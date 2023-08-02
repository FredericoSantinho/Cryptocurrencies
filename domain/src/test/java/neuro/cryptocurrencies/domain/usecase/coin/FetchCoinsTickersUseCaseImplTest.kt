package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinTickersRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class FetchCoinsTickersUseCaseImplTest {
	@Test
	fun test() = runTest {
		val getCoinTickersRepository = mock<GetCoinTickersRepository>()
		val saveCoinTickersRepository = mock<SaveCoinTickersRepository>()

		val fetchCoinsTickersUseCase =
			FetchCoinsTickersUseCaseImpl(getCoinTickersRepository, saveCoinTickersRepository)

		whenever(getCoinTickersRepository.getCoinTickers()).thenReturn(buildCoinTickersList())

		verifyNoInteractions(getCoinTickersRepository)
		verifyNoInteractions(saveCoinTickersRepository)

		fetchCoinsTickersUseCase.execute()

		verify(getCoinTickersRepository, times(1)).getCoinTickers()
		verify(saveCoinTickersRepository, times(1)).saveCoinTickers(buildCoinTickersList())
	}

	private fun buildCoinTickersList() = listOf(
		CoinTicker(
			"1",
			"Bitcoin",
			1,
			"BTC",
			25341.0
		)
	)
}