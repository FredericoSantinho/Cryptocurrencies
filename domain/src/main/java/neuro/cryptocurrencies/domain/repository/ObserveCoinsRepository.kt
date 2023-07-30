package neuro.cryptocurrencies.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinTicker

interface ObserveCoinsRepository {
	fun observeCoinsTickers(): Flow<List<CoinTicker>>
}