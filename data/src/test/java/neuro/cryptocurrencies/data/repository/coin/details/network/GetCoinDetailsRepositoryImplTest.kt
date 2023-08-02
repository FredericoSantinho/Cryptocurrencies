package neuro.cryptocurrencies.data.repository.coin.details.network

import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mocks.coinDetailsDtoMock
import neuro.cryptocurrencies.data.mocks.coinDetailsMock
import neuro.cryptocurrencies.data.repository.network.coin.details.GetCoinDetailsRepositoryImpl
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

class GetCoinDetailsRepositoryImplTest {
	@Test
	fun test() = runBlocking {
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
	fun onHttpException404() = runBlocking {
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

		val coinById = verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}

	@Test
	fun onOtherHttpException() = runBlocking {
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

		val coinById = verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}

	@Test
	fun onIOException() = runBlocking {
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

		val coinById = verify(coinPaprikaApi, times(1)).getCoinById(coinId)
	}
}