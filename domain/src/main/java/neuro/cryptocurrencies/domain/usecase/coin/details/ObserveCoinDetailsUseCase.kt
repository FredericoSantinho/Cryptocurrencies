package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface ObserveCoinDetailsUseCase {
	/**
	 * Observe cached coin details.
	 * @return a Flow that will emit a CoinDetailsWithPrice every time there is a change in the cached one.
	 */
	fun execute(coinId: String): Flow<CoinDetailsWithPrice>
}