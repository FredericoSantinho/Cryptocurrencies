package neuro.cryptocurrencies.data.repository.coin

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import retrofit2.HttpException
import java.io.IOException

class GetCoinTickerRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickerRepository {
	override suspend fun getCoinTicker(coinId: String): CoinTicker {
		try {
			return coinPaprikaApi.getCoinTicker(coinId).toDomain()
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
}