package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.repository.FetchCoinsRepository

class FetchCoinsUseCaseImpl(private val fetchCoinsRepository: FetchCoinsRepository) :
	FetchCoinsUseCase {
	override suspend fun fetchCoins() {
		fetchCoinsRepository.fetchCoins()
	}
}