package neuro.cryptocurrencies.domain.usecase.coin

interface HasCachedCoinsTickersUseCase {
	suspend fun execute(): Boolean
}