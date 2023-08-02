package neuro.cryptocurrencies.data.repository.network.coin.details

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.common.tryRequest
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository

class GetCoinDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinDetailsRepository {
	override suspend fun getCoinDetails(coinId: String): CoinDetails {
		return tryRequest {
			coinPaprikaApi.getCoinById(coinId).toDomain()
		}
	}
}