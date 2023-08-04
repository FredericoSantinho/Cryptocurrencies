package neuro.cryptocurrencies.domain.repository.coin.details

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface ObserveCoinDetailsRepository {
	/**
	 * Observe cached coin details.
	 * @return a Flow that will emit coin details with price every time there is a change in cache.
	 */
	fun observeCoinDetails(coinId: String): Flow<CoinDetailsWithPrice>
}