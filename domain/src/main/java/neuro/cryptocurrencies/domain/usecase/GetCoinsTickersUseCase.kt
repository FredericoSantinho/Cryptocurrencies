package neuro.cryptocurrencies.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface GetCoinsTickersUseCase {
	fun execute(): Flow<List<CoinTicker>>
}