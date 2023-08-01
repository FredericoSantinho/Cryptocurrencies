package neuro.cryptocurrencies.domain.repository.coin.details

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface ObserveCoinDetailsRepository {
	fun observeCoinDetails(coinId: String): Flow<CoinDetailsWithPrice>
}