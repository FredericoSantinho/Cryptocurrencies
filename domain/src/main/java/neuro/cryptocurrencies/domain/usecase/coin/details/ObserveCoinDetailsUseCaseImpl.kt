package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.coin.details.ObserveCoinDetailsRepository

class ObserveCoinDetailsUseCaseImpl(private val observeCoinDetailsRepository: ObserveCoinDetailsRepository) :
	ObserveCoinDetailsUseCase {
	override fun execute(coinId: String): Flow<CoinDetailsWithPrice> {
		return observeCoinDetailsRepository.observeCoinDetails(coinId)
	}
}