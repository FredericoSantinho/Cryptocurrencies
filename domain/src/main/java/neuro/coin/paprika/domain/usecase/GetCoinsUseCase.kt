package neuro.coin.paprika.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.coin.paprika.domain.entity.Coin

interface GetCoinsUseCase {
	fun execute(): Flow<List<Coin>>
}