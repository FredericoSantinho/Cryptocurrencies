package neuro.cryptocurrencies.data.repository.database.coin

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.mocks.database.roomCoinTickerMockList
import neuro.cryptocurrencies.domain.mocks.coinTickerMockList
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.time.Duration

class ObserveCoinsTickersRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val coinTickerDao = mock<CoinTickerDao>()

		val observeCoinsTickersRepository = ObserveCoinsTickersRepositoryImpl(coinTickerDao)

		whenever(coinTickerDao.observeCoinTickers()).thenReturn(flow {
			emit(
				roomCoinTickerMockList()
			)
		})

		verifyNoInteractions(coinTickerDao)

		val coinTickerList = observeCoinsTickersRepository.observeCoinsTickers().first()

		verify(coinTickerDao, times(1)).observeCoinTickers()

		assertEquals(coinTickerMockList(), coinTickerList)
	}
}