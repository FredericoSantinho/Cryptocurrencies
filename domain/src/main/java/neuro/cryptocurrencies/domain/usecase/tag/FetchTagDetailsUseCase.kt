package neuro.cryptocurrencies.domain.usecase.tag

interface FetchTagDetailsUseCase {
	/**
	 * Get most recent tag details and cache it.
	 * @param tagId tag id.
	 */
	suspend fun execute(tagId: String)
}