package neuro.cryptocurrencies.domain.usecase.coinTickers

import neuro.cryptocurrencies.domain.repository.coinTicker.HasCachedCoinsTickersRepository
import javax.inject.Inject

class HasCachedCoinsTickersUseCaseImpl @Inject constructor(private val hasCachedCoinsTickersRepository: HasCachedCoinsTickersRepository) :
	HasCachedCoinsTickersUseCase {
	override suspend fun execute(): Boolean {
		return hasCachedCoinsTickersRepository.hasCachedCoinsTickers()
	}
}