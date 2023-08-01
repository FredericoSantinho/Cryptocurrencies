package neuro.cryptocurrencies.domain.usecase.coin

import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsTickersRepository

class FetchCoinsTickersUseCaseImpl(private val fetchCoinsTickersRepository: FetchCoinsTickersRepository) :
	FetchCoinsTickersUseCase {
	override suspend fun fetchCoins() {
		fetchCoinsTickersRepository.fetchCoinsTickers()
	}
}