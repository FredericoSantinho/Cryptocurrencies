package neuro.cryptocurrencies.domain.repository.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface SaveTagDetailsRepository {
	/**
	 * Save tag details.
	 * @param tagDetails tag details to save.
	 */
	suspend fun saveTagDetails(tagDetails: TagDetails)
}