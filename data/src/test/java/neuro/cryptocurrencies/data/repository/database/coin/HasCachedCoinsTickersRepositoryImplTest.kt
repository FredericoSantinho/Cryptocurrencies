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

class HasCachedCoinsTickersRepositoryImplTest {

	@Test
	fun hasCachedCoinsTickers() = runTest {
		val coinTickerDao = mock<CoinTickerDao>()

		val hasCachedCoinsTickersRepository = HasCachedCoinsTickersRepositoryImpl(coinTickerDao)

		whenever(coinTickerDao.hasCoinTickers()).thenReturn(true)

		verifyNoInteractions(coinTickerDao)

		val hasCachedCoinDetails = hasCachedCoinsTickersRepository.hasCachedCoinsTickers()

		verify(coinTickerDao, times(1)).hasCoinTickers()

		assertTrue(hasCachedCoinDetails)
	}

	@Test
	fun `doesn'tHaveCachedCoinsTickers`() = runTest {
		val coinTickerDao = mock<CoinTickerDao>()

		val hasCachedCoinsTickersRepository = HasCachedCoinsTickersRepositoryImpl(coinTickerDao)

		whenever(coinTickerDao.hasCoinTickers()).thenReturn(false)

		verifyNoInteractions(coinTickerDao)

		val hasCachedCoinDetails = hasCachedCoinsTickersRepository.hasCachedCoinsTickers()

		verify(coinTickerDao, times(1)).hasCoinTickers()

		assertFalse(hasCachedCoinDetails)
	}
}