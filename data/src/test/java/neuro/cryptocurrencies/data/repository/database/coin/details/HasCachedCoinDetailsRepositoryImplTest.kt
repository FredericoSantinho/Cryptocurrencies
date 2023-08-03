package neuro.cryptocurrencies.data.repository.database.coin.details

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HasCachedCoinDetailsRepositoryImplTest {
	@Test
	fun hasCachedCoinDetails() = runTest {
		val coinDetailsDao = mock<CoinDetailsDao>()

		val hasCachedCoinDetailsRepository = HasCachedCoinDetailsRepositoryImpl(coinDetailsDao)

		val coinId = "btc-bitcoin"
		whenever(coinDetailsDao.hasCoinDetails(coinId)).thenReturn(true)

		verifyNoInteractions(coinDetailsDao)

		val hasCachedCoinDetails = hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)

		verify(coinDetailsDao, times(1)).hasCoinDetails(coinId)

		assertTrue(hasCachedCoinDetails)
	}

	@Test
	fun `doesn'tHaveCachedCoinDetails`() = runTest {
		val coinDetailsDao = mock<CoinDetailsDao>()

		val hasCachedCoinDetailsRepository = HasCachedCoinDetailsRepositoryImpl(coinDetailsDao)

		val coinId = "btc-bitcoin"
		whenever(coinDetailsDao.hasCoinDetails(coinId)).thenReturn(false)

		verifyNoInteractions(coinDetailsDao)

		val hasCachedCoinDetails = hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)

		verify(coinDetailsDao, times(1)).hasCoinDetails(coinId)

		assertFalse(hasCachedCoinDetails)
	}
}