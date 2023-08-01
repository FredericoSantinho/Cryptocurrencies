package neuro.cryptocurrencies.domain.usecase.coin.details

import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

interface GetCachedCoinDetailsUseCase {
	suspend fun execute(coinId: String): CoinDetailsWithPrice?
}