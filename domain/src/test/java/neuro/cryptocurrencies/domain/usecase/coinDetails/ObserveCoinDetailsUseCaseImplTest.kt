package neuro.cryptocurrencies.domain.usecase.coinDetails

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.mocks.coinDetailsMock
import neuro.cryptocurrencies.domain.repository.coinDetails.ObserveCoinDetailsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class ObserveCoinDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val observeCoinDetailsRepository = mock<ObserveCoinDetailsRepository>()

		val observeCoinDetailsUseCase = ObserveCoinDetailsUseCaseImpl(observeCoinDetailsRepository)

		val coinId = "btc-bitcoin"
		val price = 25241.0
		whenever(observeCoinDetailsRepository.observeCoinDetails(coinId)).thenReturn(flow {
			emit(
				CoinDetailsWithPrice(coinDetailsMock(), price)
			)
		})

		verifyNoInteractions(observeCoinDetailsRepository)

		val coinDetailsWithPrice = observeCoinDetailsUseCase.execute(coinId).first()

		verify(observeCoinDetailsRepository, times(1)).observeCoinDetails(coinId)

		assertEquals(CoinDetailsWithPrice(coinDetailsMock(), price), coinDetailsWithPrice)
	}
}