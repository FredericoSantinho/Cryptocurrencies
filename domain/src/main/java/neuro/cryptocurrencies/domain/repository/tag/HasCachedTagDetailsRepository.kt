package neuro.cryptocurrencies.domain.repository.tag

interface HasCachedTagDetailsRepository {
	suspend fun hasCachedTagDetails(tagId: String): Boolean
}