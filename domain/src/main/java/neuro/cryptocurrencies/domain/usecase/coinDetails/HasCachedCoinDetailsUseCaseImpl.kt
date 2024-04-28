package neuro.cryptocurrencies.domain.usecase.coinDetails

import neuro.cryptocurrencies.domain.repository.coin.details.HasCachedCoinDetailsRepository

class HasCachedCoinDetailsUseCaseImpl(private val hasCachedCoinDetailsRepository: HasCachedCoinDetailsRepository) :
	HasCachedCoinDetailsUseCase {
	override suspend fun execute(coinId: String): Boolean {
		return hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)
	}
}