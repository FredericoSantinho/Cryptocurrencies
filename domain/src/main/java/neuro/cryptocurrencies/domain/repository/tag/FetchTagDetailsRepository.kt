package neuro.cryptocurrencies.domain.repository.tag

interface FetchTagDetailsRepository {
	suspend fun fetchTagDetails(tagId: String)
}