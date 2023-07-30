package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.domain.repository.ObserveCoinsRepository

class GetCoinsUseCaseImpl(private val observeCoinsRepository: ObserveCoinsRepository) :
	GetCoinsUseCase {
	override fun execute(): Flow<List<CoinTicker>> {
		return observeCoinsRepository.observeCoinsTickers()
	}
}