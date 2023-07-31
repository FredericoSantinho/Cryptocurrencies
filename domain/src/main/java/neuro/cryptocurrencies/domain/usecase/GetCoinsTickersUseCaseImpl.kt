package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.ObserveCoinsTickersRepository

class GetCoinsTickersUseCaseImpl(private val observeCoinsTickersRepository: ObserveCoinsTickersRepository) :
	GetCoinsTickersUseCase {
	override fun execute(): Flow<List<CoinTicker>> {
		return observeCoinsTickersRepository.observeCoinsTickers()
	}
}