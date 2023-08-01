package neuro.cryptocurrencies.domain.usecase.coin.details

interface HasCachedCoinDetailsUseCase {
	suspend fun execute(coinId: String): Boolean
}