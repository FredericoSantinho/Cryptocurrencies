package neuro.coin.paprika.domain.usecase

import neuro.coin.paprika.domain.entity.Coin

interface GetCoinsUseCase {
	suspend fun execute(): List<Coin>
}