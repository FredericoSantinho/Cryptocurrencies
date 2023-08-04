package neuro.cryptocurrencies.domain.usecase.tag

interface HasCachedTagDetailsUseCase {
	/**
	 * Checks if there are cached tag details.
	 * @return true if there are cached tag details. False otherwise.
	 */
	suspend fun execute(tagId: String): Boolean
}