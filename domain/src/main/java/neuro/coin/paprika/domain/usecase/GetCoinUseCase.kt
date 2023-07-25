package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.model.CoinDetails

interface GetCoinUseCase {
	suspend fun execute(coinId: String): CoinDetails
}