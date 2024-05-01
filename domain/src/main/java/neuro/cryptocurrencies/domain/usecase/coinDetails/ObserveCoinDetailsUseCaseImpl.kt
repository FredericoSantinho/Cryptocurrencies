package neuro.cryptocurrencies.domain.usecase.coinDetails

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.repository.coin.details.ObserveCoinDetailsRepository
import javax.inject.Inject

class ObserveCoinDetailsUseCaseImpl @Inject constructor(private val observeCoinDetailsRepository: ObserveCoinDetailsRepository) :
	ObserveCoinDetailsUseCase {
	override fun execute(coinId: String): Flow<CoinDetailsWithPrice> {
		return observeCoinDetailsRepository.observeCoinDetails(coinId)
	}
}