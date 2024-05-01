package neuro.cryptocurrencies.domain.usecase.coinDetails

import neuro.cryptocurrencies.domain.repository.coinDetails.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coinDetails.SaveCoinDetailsWithPriceRepository
import neuro.cryptocurrencies.domain.repository.coinTicker.GetCoinTickerRepository
import javax.inject.Inject

class FetchCoinDetailsUseCaseImpl @Inject constructor(
	private val getCoinTickerRepository: GetCoinTickerRepository,
	private val getCoinDetailsRepository: GetCoinDetailsRepository,
	private val saveCoinDetailsWithPriceRepository: SaveCoinDetailsWithPriceRepository
) : FetchCoinDetailsUseCase {
	override suspend fun execute(coinId: String) {
		val coinDetails = getCoinDetailsRepository.getCoinDetails(coinId)
		val price = getCoinTickerRepository.getCoinTicker(coinId).price

		saveCoinDetailsWithPriceRepository.saveCoinDetailsWithPrice(coinDetails, price)
	}
}