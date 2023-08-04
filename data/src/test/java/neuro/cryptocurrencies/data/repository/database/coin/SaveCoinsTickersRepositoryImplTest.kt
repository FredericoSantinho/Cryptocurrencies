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
import kotlin.time.Duration

class SaveCoinsTickersRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val coinTickerDao = mock<CoinTickerDao>()

		val saveCoinTickersRepository = SaveCoinsTickersRepositoryImpl(coinTickerDao)

		verifyNoInteractions(coinTickerDao)

		saveCoinTickersRepository.saveCoinsTickers(coinTickerMockList())

		verify(coinTickerDao, times(1)).upsertCoinTickers(roomCoinTickerMockList())
	}

}