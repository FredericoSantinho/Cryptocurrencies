package neuro.coin.paprika.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.coin.paprika.domain.entity.Coin
import neuro.coin.paprika.domain.repository.ObserveCoinsRepository

class GetCoinsUseCaseImpl(private val observeCoinsRepository: ObserveCoinsRepository) :
	GetCoinsUseCase {
	override fun execute(): Flow<List<Coin>> {
		return observeCoinsRepository.observeCoins()
	}
}