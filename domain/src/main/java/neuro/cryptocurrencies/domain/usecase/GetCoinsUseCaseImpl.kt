package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.Coin
import neuro.cryptocurrencies.domain.repository.ObserveCoinsRepository

class GetCoinsUseCaseImpl(private val observeCoinsRepository: ObserveCoinsRepository) :
	GetCoinsUseCase {
	override fun execute(): Flow<List<Coin>> {
		return observeCoinsRepository.observeCoins()
	}
}