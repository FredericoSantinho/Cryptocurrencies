package neuro.cryptocurrencies.data.repository.network.coinTicker.coinDetails

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mocks.network.coinDetailsDtoMock
import neuro.cryptocurrencies.data.repository.network.coinDetails.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.domain.mocks.coinDetailsMock
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import okhttp3.ResponseBody
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
import kotlin.test.assertEquals
import kotlin.time.Duration

class GetCoinDetailsRepositoryImplTest {

	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinDetailsRepository = GetCoinDetailsRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinById(coinId)).thenReturn(coinDetailsDtoMock())

		verifyNoInteractions(coinPaprikaApi)

		val coinDetails = getCoinDetailsRepository.getCoinDetails(coinId)

		verify(coinPaprikaApi, times(1)).getCoinById(coinId)

		assertEquals(coinDetailsMock(), coinDetails)
	}

	@Test
	fun onHttpException404() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinDetailsRepository = GetCoinDetailsRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinById(coinId)).thenThrow(
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
				getCoinDetailsRepository.getCoinDetails(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}

	@Test
	fun onOtherHttpException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinDetailsRepository = GetCoinDetailsRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinById(coinId)).thenThrow(HttpException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows("An unexpected error occurred", ErrorRetrievingDataException::class.java) {
			runBlocking {
				getCoinDetailsRepository.getCoinDetails(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}

	@Test
	fun onIOException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinDetailsRepository = GetCoinDetailsRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinById(coinId)).thenThrow(IOException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows(
			"Couldn't reach server. Check your internet connection.",
			ErrorRetrievingDataException::class.java
		) {
			runBlocking {
				getCoinDetailsRepository.getCoinDetails(coinId)
			}
		}

		verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}
}