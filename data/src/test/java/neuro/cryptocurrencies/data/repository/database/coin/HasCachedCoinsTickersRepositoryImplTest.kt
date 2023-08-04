package neuro.cryptocurrencies.data.repository.database.coin

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.time.Duration

class HasCachedCoinsTickersRepositoryImplTest {

	@Test
	fun hasCachedCoinsTickers() = runTest(timeout = Duration.parse("1m")) {
		val coinTickerDao = mock<CoinTickerDao>()

		val hasCachedCoinsTickersRepository = HasCachedCoinsTickersRepositoryImpl(coinTickerDao)

		whenever(coinTickerDao.hasCoinTickers()).thenReturn(true)

		verifyNoInteractions(coinTickerDao)

		val hasCachedCoinsTickers = hasCachedCoinsTickersRepository.hasCachedCoinsTickers()

		verify(coinTickerDao, times(1)).hasCoinTickers()

		assertTrue(hasCachedCoinsTickers)
	}

	@Test
	fun `doesn'tHaveCachedCoinsTickers`() = runTest(timeout = Duration.parse("1m")) {
		val coinTickerDao = mock<CoinTickerDao>()

		val hasCachedCoinsTickersRepository = HasCachedCoinsTickersRepositoryImpl(coinTickerDao)

		whenever(coinTickerDao.hasCoinTickers()).thenReturn(false)

		verifyNoInteractions(coinTickerDao)

		val hasCachedCoinsTickers = hasCachedCoinsTickersRepository.hasCachedCoinsTickers()

		verify(coinTickerDao, times(1)).hasCoinTickers()

		assertFalse(hasCachedCoinsTickers)
	}
}