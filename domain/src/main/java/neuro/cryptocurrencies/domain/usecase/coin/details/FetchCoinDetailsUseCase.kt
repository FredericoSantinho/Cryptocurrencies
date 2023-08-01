package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.CoroutineScope

interface FetchCoinDetailsUseCase {
	suspend fun execute(coinId: String, coroutineScope: CoroutineScope)
}