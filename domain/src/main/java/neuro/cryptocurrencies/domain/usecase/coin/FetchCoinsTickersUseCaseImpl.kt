package neuro.cryptocurrencies.domain.usecase.coin

import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinTickersRepository

class FetchCoinsTickersUseCaseImpl(
	private val getCoinTickersRepository: GetCoinTickersRepository,
	private val saveCoinTickersRepository: SaveCoinTickersRepository
) :
	FetchCoinsTickersUseCase {
	override suspend fun execute() {
		val coinTickers = getCoinTickersRepository.getCoinTickers()
		saveCoinTickersRepository.saveCoinTickers(coinTickers)
	}
}