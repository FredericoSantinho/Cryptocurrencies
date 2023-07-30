package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.repository.GetCoinByIdRepository

class GetCoinDetailsUseCaseImpl(private val getCoinByIdRepository: GetCoinByIdRepository) :
	GetCoinDetailsUseCase {
	override suspend fun execute(coinId: String): CoinDetails {
		return getCoinByIdRepository.getCoinById(coinId)
	}
}