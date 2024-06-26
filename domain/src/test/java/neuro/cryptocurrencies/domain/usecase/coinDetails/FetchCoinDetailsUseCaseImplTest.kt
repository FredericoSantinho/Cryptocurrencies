package neuro.cryptocurrencies.domain.usecase.coinDetails

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.mocks.coinDetailsMock
import neuro.cryptocurrencies.domain.repository.coinDetails.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coinDetails.SaveCoinDetailsWithPriceRepository
import neuro.cryptocurrencies.domain.repository.coinTicker.GetCoinTickerRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class FetchCoinDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val getCoinTickerRepository = mock<GetCoinTickerRepository>()
		val getCoinDetailsRepository = mock<GetCoinDetailsRepository>()
		val saveCoinDetailsWithPriceRepository = mock<SaveCoinDetailsWithPriceRepository>()

		val fetchCoinDetailsUseCase = FetchCoinDetailsUseCaseImpl(
			getCoinTickerRepository,
			getCoinDetailsRepository,
			saveCoinDetailsWithPriceRepository
		)

		val coinId = "btc-bitcoin"
		val price = 25423.0
		whenever(getCoinTickerRepository.getCoinTicker(coinId)).thenReturn(
			CoinTicker(
				"1",
				"Bitcoin",
				1,
				"BTC",
				price
			)
		)
		val coinDetails = coinDetailsMock()
		whenever(getCoinDetailsRepository.getCoinDetails(coinId)).thenReturn(
			coinDetails
		)

		verifyNoInteractions(getCoinTickerRepository)
		verifyNoInteractions(getCoinDetailsRepository)
		verifyNoInteractions(saveCoinDetailsWithPriceRepository)

		fetchCoinDetailsUseCase.execute(coinId)

		verify(getCoinTickerRepository, times(1)).getCoinTicker(coinId)
		verify(getCoinDetailsRepository, times(1)).getCoinDetails(coinId)
		verify(saveCoinDetailsWithPriceRepository, times(1)).saveCoinDetailsWithPrice(
			coinDetails,
			price
		)
	}
}