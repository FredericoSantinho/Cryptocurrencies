package neuro.cryptocurrencies.domain.usecase.coin

import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository

class HasCachedCoinsTickersUseCaseImpl(private val hasCachedCoinsTickersRepository: HasCachedCoinsTickersRepository) :
	HasCachedCoinsTickersUseCase {
	override suspend fun execute(): Boolean {
		return hasCachedCoinsTickersRepository.hasCachedCoinsTickers()
	}
}