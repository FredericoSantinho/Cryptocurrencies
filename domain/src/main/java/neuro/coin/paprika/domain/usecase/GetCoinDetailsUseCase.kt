package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.model.CoinDetails

interface GetCoinDetailsUseCase {
	suspend fun execute(coinId: String): CoinDetails
}