package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.model.CoinDetails
import neuro.coin.paprika.domain.repository.GetCoinRepository

class GetCoinUseCaseImpl(private val getCoinRepository: GetCoinRepository) : GetCoinUseCase {
	override suspend fun execute(coinId: String): CoinDetails {
		return getCoinRepository.getCoinById(coinId)
	}
}