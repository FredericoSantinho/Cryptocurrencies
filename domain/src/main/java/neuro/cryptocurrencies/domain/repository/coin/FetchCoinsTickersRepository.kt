package neuro.cryptocurrencies.domain.repository.coin

interface FetchCoinsTickersRepository {
	suspend fun fetchCoinsTickers()
}