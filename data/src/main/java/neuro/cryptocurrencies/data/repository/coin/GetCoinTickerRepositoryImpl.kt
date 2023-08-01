package neuro.cryptocurrencies.data.repository.coin

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository

class GetCoinTickerRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetCoinTickerRepository {
	override suspend fun getCoinTicker(coinId: String): CoinTicker {
		return coinPaprikaApi.getCoinTicker(coinId).toDomain()
	}
}