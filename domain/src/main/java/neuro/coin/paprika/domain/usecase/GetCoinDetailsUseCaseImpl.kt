package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.entity.CoinDetails
import neuro.coin.paprika.domain.repository.GetCoinByIdRepository

class GetCoinDetailsUseCaseImpl(private val getCoinByIdRepository: GetCoinByIdRepository) :
	GetCoinDetailsUseCase {
	override suspend fun execute(coinId: String): CoinDetails {
		return getCoinByIdRepository.getCoinById(coinId)
	}
}