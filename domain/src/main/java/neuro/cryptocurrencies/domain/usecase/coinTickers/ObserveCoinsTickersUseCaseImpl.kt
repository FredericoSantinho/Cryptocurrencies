package neuro.cryptocurrencies.domain.usecase.coinTickers

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository

class ObserveCoinsTickersUseCaseImpl(private val observeCoinsTickersRepository: ObserveCoinsTickersRepository) :
	ObserveCoinsTickersUseCase {
	override fun execute(): Flow<List<CoinTicker>> {
		return observeCoinsTickersRepository.observeCoinsTickers()
	}
}