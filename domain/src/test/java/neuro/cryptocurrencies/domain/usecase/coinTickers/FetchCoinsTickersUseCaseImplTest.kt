package neuro.cryptocurrencies.domain.usecase.coinTickers

import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinsTickersRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class FetchCoinsTickersUseCaseImplTest {
	@Test
	fun test() = runBlocking {
		val getCoinTickersRepository = mock<GetCoinTickersRepository>()
		val saveCoinsTickersRepository = mock<SaveCoinsTickersRepository>()

		val fetchCoinsTickersUseCase =
			FetchCoinsTickersUseCaseImpl(getCoinTickersRepository, saveCoinsTickersRepository)

		whenever(getCoinTickersRepository.getCoinTickers()).thenReturn(buildCoinTickersList())

		verifyNoInteractions(getCoinTickersRepository)
		verifyNoInteractions(saveCoinsTickersRepository)

		fetchCoinsTickersUseCase.execute()

		verify(getCoinTickersRepository, times(1)).getCoinTickers()
		verify(saveCoinsTickersRepository, times(1)).saveCoinsTickers(buildCoinTickersList())
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