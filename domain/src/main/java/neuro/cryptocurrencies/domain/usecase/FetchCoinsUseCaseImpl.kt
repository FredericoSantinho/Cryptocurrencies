package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.repository.FetchCoinsRepository

class FetchCoinsUseCaseImpl(private val fetchCoinsRepository: FetchCoinsRepository) :
	FetchCoinsUseCase {
	override suspend fun fetchCoins() {
		fetchCoinsRepository.fetchCoins()
	}
}