package neuro.cryptocurrencies.domain.usecase.tagDetails

interface HasCachedTagDetailsUseCase {
	/**
	 * Checks if there are cached tag details.
	 * @param tagId tag id.
	 * @return true if there are cached tag details. False otherwise.
	 */
	suspend fun execute(tagId: String): Boolean
}