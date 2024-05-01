package neuro.cryptocurrencies.data.repository.network.coinDetails

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.util.makeRequest
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.coinDetails.GetCoinDetailsRepository
import javax.inject.Inject

class GetCoinDetailsRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinDetailsRepository {
	override suspend fun getCoinDetails(coinId: String): CoinDetails {
		return makeRequest {
			coinPaprikaApi.getCoinById(coinId).toDomain()
		}
	}
}