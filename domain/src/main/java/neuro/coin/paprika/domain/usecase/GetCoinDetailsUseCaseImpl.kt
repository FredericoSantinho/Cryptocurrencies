package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.entity.CoinDetails
import neuro.coin.paprika.domain.repository.GetCoinRepository

class GetCoinDetailsUseCaseImpl(private val getCoinRepository: GetCoinRepository) :
	GetCoinDetailsUseCase {
	override suspend fun execute(coinId: String): CoinDetails {
		return getCoinRepository.getCoinById(coinId)
	}
}