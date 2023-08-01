package neuro.cryptocurrencies.domain.usecase.coin.details

import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.coin.details.GetCachedCoinDetailsRepository

class GetCachedCoinDetailsUseCaseImpl(private val getCachedCoinDetailsRepository: GetCachedCoinDetailsRepository) :
	GetCachedCoinDetailsUseCase {
	override suspend fun execute(coinId: String): CoinDetailsWithPrice? {
		return getCachedCoinDetailsRepository.getCachedCoinDetails(coinId)
	}
}