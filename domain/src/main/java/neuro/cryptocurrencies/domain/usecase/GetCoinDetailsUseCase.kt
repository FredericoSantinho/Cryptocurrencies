package neuro.cryptocurrencies.domain.usecase

import neuro.cryptocurrencies.domain.entity.CoinDetails

interface GetCoinDetailsUseCase {
	suspend fun execute(coinId: String): CoinDetails
}