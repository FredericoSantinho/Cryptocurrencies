package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.CoroutineScope
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface GetCoinDetailsWithPriceUseCase {
	suspend fun execute(coinId: String, coroutineScope: CoroutineScope): CoinDetailsWithPrice
}