package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.GetCoinTickerRepository

class GetCoinDetailsWithPriceUseCaseImpl(
	private val getCoinDetailsRepository: GetCoinDetailsRepository,
	private val getCoinTickerRepository: GetCoinTickerRepository
) :
	GetCoinDetailsWithPriceUseCase {
	override suspend fun execute(
		coinId: String,
		coroutineScope: CoroutineScope
	): CoinDetailsWithPrice {
		val asyncCoinDetails = coroutineScope.async(Dispatchers.IO) {
			getCoinDetailsRepository.getCoinDetails(coinId)
		}
		val asyncCoinTicker = coroutineScope.async(Dispatchers.IO) {
			getCoinTickerRepository.getCoinTicker(coinId)
		}
		val coinDetails = asyncCoinDetails.await()
		val coinTicker = asyncCoinTicker.await()

		return CoinDetailsWithPrice(coinDetails, coinTicker.price)
	}
}