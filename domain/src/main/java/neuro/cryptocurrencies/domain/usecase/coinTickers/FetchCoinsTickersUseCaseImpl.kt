package neuro.cryptocurrencies.domain.usecase.coinTickers

import neuro.cryptocurrencies.domain.repository.coinTicker.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coinTicker.SaveCoinsTickersRepository
import javax.inject.Inject

class FetchCoinsTickersUseCaseImpl @Inject constructor(
	private val getCoinTickersRepository: GetCoinTickersRepository,
	private val saveCoinsTickersRepository: SaveCoinsTickersRepository
) :
	FetchCoinsTickersUseCase {
	override suspend fun execute() {
		val coinTickers = getCoinTickersRepository.getCoinTickers()
		saveCoinsTickersRepository.saveCoinsTickers(coinTickers)
	}
}