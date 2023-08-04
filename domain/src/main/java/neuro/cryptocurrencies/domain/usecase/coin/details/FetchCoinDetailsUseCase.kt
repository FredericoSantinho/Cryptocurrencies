package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.CoroutineScope

interface FetchCoinDetailsUseCase {
	/**
	 * Get most recent coin details and cache it.
	 * @param coinId coin id.
	 * @param coroutineScope coroutineScope where asynchronous work will be executed.
	 */
	suspend fun execute(coinId: String, coroutineScope: CoroutineScope)
}