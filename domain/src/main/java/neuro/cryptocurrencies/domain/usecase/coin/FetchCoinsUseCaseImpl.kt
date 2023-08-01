package neuro.cryptocurrencies.domain.usecase.coin

import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsRepository

class FetchCoinsUseCaseImpl(private val fetchCoinsRepository: FetchCoinsRepository) :
	FetchCoinsUseCase {
	override suspend fun fetchCoins() {
		fetchCoinsRepository.fetchCoins()
	}
}