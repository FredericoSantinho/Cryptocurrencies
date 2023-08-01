package neuro.cryptocurrencies.domain.repository.tag

interface FetchTagRepository {
	suspend fun fetchTag(tagId: String)
}