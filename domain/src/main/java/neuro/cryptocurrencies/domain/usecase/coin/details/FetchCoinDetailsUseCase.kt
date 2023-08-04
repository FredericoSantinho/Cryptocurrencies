package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.CoroutineScope

interface FetchCoinDetailsUseCase {
	/**
	 * Get last coin details and cache it.
	 * @param coinId coin id.
	 * @param coroutineScope coroutineScope where calls will be made.
	 */
	suspend fun execute(coinId: String, coroutineScope: CoroutineScope)
}