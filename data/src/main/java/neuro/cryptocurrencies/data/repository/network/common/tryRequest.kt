package neuro.cryptocurrencies.data.repository.network.common

import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> tryRequest(request: suspend () -> T): T {
	try {
		return request()
	} catch (e: HttpException) {
		if (e.code() == 404) {
			throw NoDataAvailableException(e.localizedMessage ?: "Not found")
		} else {
			throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
		}
	} catch (e: IOException) {
		throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
	}
}