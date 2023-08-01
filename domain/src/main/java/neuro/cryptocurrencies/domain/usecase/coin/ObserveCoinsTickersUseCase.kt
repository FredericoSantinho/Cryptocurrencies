package neuro.cryptocurrencies.domain.usecase.coin

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsTickersUseCase {
	fun execute(): Flow<List<CoinTicker>>
}