package neuro.cryptocurrencies.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.Coin

interface ObserveCoinsRepository {
	fun observeCoins(): Flow<List<Coin>>
}