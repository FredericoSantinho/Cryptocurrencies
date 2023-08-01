package neuro.cryptocurrencies.domain.usecase.coin.details

import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface ObserveCoinDetailsUseCase {
	fun execute(coinId: String): Flow<CoinDetailsWithPrice>
}