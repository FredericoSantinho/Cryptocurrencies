package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.entity.Coin
import neuro.coin.paprika.domain.repository.GetCoinsRepository

class GetCoinsUseCaseImpl(private val getCoinsRepository: GetCoinsRepository) : GetCoinsUseCase {
	override suspend fun execute(): List<Coin> {
		return getCoinsRepository.getCoins()
	}
}