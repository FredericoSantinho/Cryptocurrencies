package neuro.cryptocurrencies.data.repository.network.tag

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mocks.network.tagDetailsDtoMock
import neuro.cryptocurrencies.domain.mocks.tagDetailsMock
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

class GetTagDetailsRepositoryImplTest {
	@Test
	fun test() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getTagDetailsRepository = GetTagDetailsRepositoryImpl(coinPaprikaApi)

		val tagId = "segwit"
		whenever(coinPaprikaApi.getTagDetails(tagId)).thenReturn(tagDetailsDtoMock())

		verifyNoInteractions(coinPaprikaApi)

		val tagDetails = getTagDetailsRepository.getTagDetails(tagId)

		verify(coinPaprikaApi, times(1)).getTagDetails(tagId)

		assertEquals(tagDetailsMock(), tagDetails)
	}

	@Test
	fun onHttpException404() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getTagDetailsRepository = GetTagDetailsRepositoryImpl(coinPaprikaApi)

		val tagId = "segwit"
		whenever(coinPaprikaApi.getTagDetails(tagId)).thenThrow(
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
				getTagDetailsRepository.getTagDetails(tagId)
			}
		}

		verify(coinPaprikaApi, times(1)).getTagDetails(tagId)
	}

	@Test
	fun onOtherHttpException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getTagDetailsRepository = GetTagDetailsRepositoryImpl(coinPaprikaApi)

		val tagId = "segwit"
		whenever(coinPaprikaApi.getTagDetails(tagId)).thenThrow(HttpException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows("An unexpected error occurred", ErrorRetrievingDataException::class.java) {
			runBlocking {
				getTagDetailsRepository.getTagDetails(tagId)
			}
		}

		verify(coinPaprikaApi, times(1)).getTagDetails(tagId)
	}

	@Test
	fun onIOException() = runTest(timeout = Duration.parse("1m")) {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getTagDetailsRepository = GetTagDetailsRepositoryImpl(coinPaprikaApi)

		val tagId = "segwit"
		whenever(coinPaprikaApi.getTagDetails(tagId)).thenThrow(IOException::class.java)

		verifyNoInteractions(coinPaprikaApi)

		assertThrows(
			"Couldn't reach server. Check your internet connection.",
			ErrorRetrievingDataException::class.java
		) {
			runBlocking {
				getTagDetailsRepository.getTagDetails(tagId)
			}
		}

		verify(coinPaprikaApi, times(1)).getTagDetails(tagId)
	}
}