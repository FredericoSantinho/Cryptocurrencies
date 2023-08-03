package neuro.cryptocurrencies.data.repository.database.coin

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.mocks.database.roomCoinTickerMockList
import neuro.cryptocurrencies.domain.mocks.coinTickerMockList
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

class SaveCoinTickersRepositoryImplTest {

	@Test
	fun test() = runTest {
		val coinTickerDao = mock<CoinTickerDao>()

		val saveCoinTickersRepository = SaveCoinTickersRepositoryImpl(coinTickerDao)

		verifyNoInteractions(coinTickerDao)

		saveCoinTickersRepository.saveCoinTickers(coinTickerMockList())

		verify(coinTickerDao, times(1)).upsertCoinTickers(roomCoinTickerMockList())
	}

}