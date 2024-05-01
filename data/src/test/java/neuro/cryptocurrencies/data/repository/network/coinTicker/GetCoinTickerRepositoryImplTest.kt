package neuro.cryptocurrencies.data.repository.network.coinTicker

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mocks.network.coinTickerDtoMock
import neuro.cryptocurrencies.domain.mocks.coinTickerMock
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.time.Duration

class GetCoinTickerRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinTickerRepository = GetCoinTickerRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinTicker(coinId)).thenReturn(coinTickerDtoMock())

		verifyNoInteractions(coinPaprikaApi)

		val coinTicker = getCoinTickerRepository.getCoinTicker(coinId)

		verify(coinPaprikaApi, times(1)).getCoinTicker(coinId)

		assertEquals(coinTickerMock(), coinTicker)
	}

	@Test
	fun onHttpException404() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinTickerRepository = GetCoinTickerRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinTicker(coinId)).thenThrow(
			HttpException(
				Response.error<Int>(
					404,
					ResponseBody.create(null, "")
				)
			)
		)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows("Not found", NoDataAvailableException::class.java) {
			runBlocking {
				getCoinTickerRepository.getCoinTicker(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinTicker(coinId)
	}

	@Test
	fun onOtherHttpException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinTickerRepository = GetCoinTickerRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinTicker(coinId)).thenThrow(HttpException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows("An unexpected error occurred", ErrorRetrievingDataException::class.java) {
			runBlocking {
				getCoinTickerRepository.getCoinTicker(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinTicker(coinId)
	}

	@Test
	fun onIOException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinTickerRepository = GetCoinTickerRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinTicker(coinId)).thenThrow(IOException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows(
			"Couldn't reach server. Check your internet connection.",
			ErrorRetrievingDataException::class.java
		) {
			runBlocking {
				getCoinTickerRepository.getCoinTicker(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinTicker(coinId)
	}
}