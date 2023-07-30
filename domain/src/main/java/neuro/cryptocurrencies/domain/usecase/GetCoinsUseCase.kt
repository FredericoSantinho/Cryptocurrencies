package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.Coin

interface GetCoinsUseCase {
	fun execute(): Flow<List<Coin>>
}