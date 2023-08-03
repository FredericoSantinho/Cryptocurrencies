package neuro.cryptocurrencies.data.repository.database.coin.details

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.mocks.database.roomCoinDetailsWithPriceWithTagsAndTeamMock
import neuro.cryptocurrencies.domain.mocks.coinDetailsWithPriceMock
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class ObserveCoinDetailsRepositoryImplTest {
	@Test
	fun testNotNullResult() = runTest {
		val coinDetailsDao = mock<CoinDetailsDao>()

		val observeCoinDetailsRepository = ObserveCoinDetailsRepositoryImpl(coinDetailsDao)

		val coinId = "btc-bitcoin"
		whenever(coinDetailsDao.observeCoinDetails(coinId)).thenReturn(flow {
			emit(
				roomCoinDetailsWithPriceWithTagsAndTeamMock()
			)
		})

		verifyNoInteractions(coinDetailsDao)

		val coinDetailsWithPrice = observeCoinDetailsRepository.observeCoinDetails(coinId).first()

		verify(coinDetailsDao, times(1)).observeCoinDetails(coinId)

		assertEquals(coinDetailsWithPriceMock(), coinDetailsWithPrice)
	}

	@Test
	fun testNullResult() = runTest {
		val coinDetailsDao = mock<CoinDetailsDao>()

		val observeCoinDetailsRepository = ObserveCoinDetailsRepositoryImpl(coinDetailsDao)

		val coinId = "btc-bitcoin"
		whenever(coinDetailsDao.observeCoinDetails(coinId)).thenReturn(flow {
			emit(
				null
			)
		})

		verifyNoInteractions(coinDetailsDao)

		val coinDetailsWithPriceList = observeCoinDetailsRepository.observeCoinDetails(coinId).toList()

		verify(coinDetailsDao, times(1)).observeCoinDetails(coinId)

		assertEquals(0, coinDetailsWithPriceList.size)
	}
}