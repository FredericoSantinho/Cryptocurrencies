package neuro.cryptocurrencies.data.repository.network.coinTicker

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.util.makeRequest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coinTicker.GetCoinTickersRepository
import javax.inject.Inject

class GetCoinTickersRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickersRepository {
	override suspend fun getCoinTickers(): List<CoinTicker> {
		return makeRequest {
			coinPaprikaApi.getCoinsTickers().filter { it.rank != 0 }.map { it.toDomain() }
		}
	}
}