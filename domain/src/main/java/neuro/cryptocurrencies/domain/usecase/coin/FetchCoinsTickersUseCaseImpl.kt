package neuro.cryptocurrencies.domain.usecase.coin

import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinsTickersRepository

class FetchCoinsTickersUseCaseImpl(
	private val getCoinTickersRepository: GetCoinTickersRepository,
	private val saveCoinsTickersRepository: SaveCoinsTickersRepository
) :
	FetchCoinsTickersUseCase {
	override suspend fun execute() {
		val coinTickers = getCoinTickersRepository.getCoinTickers()
		saveCoinsTickersRepository.saveCoinsTickers(coinTickers)
	}
}