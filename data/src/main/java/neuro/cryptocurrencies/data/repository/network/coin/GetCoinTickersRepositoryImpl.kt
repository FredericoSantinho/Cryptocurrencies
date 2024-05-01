package neuro.cryptocurrencies.data.repository.network.coin

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.common.tryRequest
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import javax.inject.Inject

class GetCoinTickersRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickersRepository {
	override suspend fun getCoinTickers(): List<CoinTicker> {
		return tryRequest {
			coinPaprikaApi.getCoinsTickers().filter { it.rank != 0 }.map { it.toDomain() }
		}
	}
}