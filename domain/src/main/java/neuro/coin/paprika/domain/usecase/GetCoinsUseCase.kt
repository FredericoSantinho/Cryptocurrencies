package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.model.Coin

interface GetCoinsUseCase {
	suspend fun execute(): List<Coin>
}