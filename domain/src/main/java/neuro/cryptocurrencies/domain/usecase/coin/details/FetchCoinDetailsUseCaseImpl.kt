package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.SaveCoinDetailsWithPriceRepository

class FetchCoinDetailsUseCaseImpl(
	private val getCoinTickerRepository: GetCoinTickerRepository,
	private val getCoinDetailsRepository: GetCoinDetailsRepository,
	private val saveCoinDetailsWithPriceRepository: SaveCoinDetailsWithPriceRepository
) : FetchCoinDetailsUseCase {
	override suspend fun execute(coinId: String, coroutineScope: CoroutineScope) {
		val priceDeferred = coroutineScope.async(Dispatchers.IO) {
			getCoinTickerRepository.getCoinTicker(coinId).price
		}
		val coinDetailsDtoDeferred = coroutineScope.async(Dispatchers.IO) {
			getCoinDetailsRepository.getCoinDetails(coinId)
		}
		val coinDetails = coinDetailsDtoDeferred.await()
		val price = priceDeferred.await()
		saveCoinDetailsWithPriceRepository.saveCoinDetailsWithPrice(coinDetails, price)
	}
}