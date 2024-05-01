package neuro.cryptocurrencies.domain.usecase.coinTickers

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.coinTicker.ObserveCoinsTickersRepository
import javax.inject.Inject

class ObserveCoinsTickersUseCaseImpl @Inject constructor(private val observeCoinsTickersRepository: ObserveCoinsTickersRepository) :
	ObserveCoinsTickersUseCase {
	override fun execute(): Flow<List<CoinTicker>> {
		return observeCoinsTickersRepository.observeCoinsTickers()
	}
}