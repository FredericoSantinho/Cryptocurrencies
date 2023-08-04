package neuro.cryptocurrencies.domain.repository.tag

interface HasCachedTagDetailsRepository {
	/**
	 * Checks if there are cached tag details.
	 * @param tagId tag id.
	 * @return true if there are cached tag details. False otherwise.
	 */
	suspend fun hasCachedTagDetails(tagId: String): Boolean
}