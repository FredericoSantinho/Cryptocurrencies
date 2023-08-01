package neuro.cryptocurrencies.domain.repository

interface FetchTagRepository {
	suspend fun fetchTag(tagId: String)
}