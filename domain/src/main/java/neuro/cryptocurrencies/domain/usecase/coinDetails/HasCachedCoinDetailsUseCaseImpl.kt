package neuro.cryptocurrencies.domain.usecase.coinDetails

import neuro.cryptocurrencies.domain.repository.coin.details.HasCachedCoinDetailsRepository
import javax.inject.Inject

class HasCachedCoinDetailsUseCaseImpl @Inject constructor(private val hasCachedCoinDetailsRepository: HasCachedCoinDetailsRepository) :
	HasCachedCoinDetailsUseCase {
	override suspend fun execute(coinId: String): Boolean {
		return hasCachedCoinDetailsRepository.hasCachedCoinDetails(coinId)
	}
}