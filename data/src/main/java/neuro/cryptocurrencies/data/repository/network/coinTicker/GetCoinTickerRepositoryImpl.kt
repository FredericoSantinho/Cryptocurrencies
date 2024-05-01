package neuro.cryptocurrencies.data.repository.network.coinTicker

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.util.makeRequest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coinTicker.GetCoinTickerRepository
import javax.inject.Inject

class GetCoinTickerRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickerRepository {
	override suspend fun getCoinTicker(coinId: String): CoinTicker {
		return makeRequest {
			coinPaprikaApi.getCoinTicker(coinId).toDomain()
		}
	}
}