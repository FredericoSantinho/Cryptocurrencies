package neuro.cryptocurrencies.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsTickersRepository {
	fun observeCoinsTickers(): Flow<List<CoinTicker>>
}