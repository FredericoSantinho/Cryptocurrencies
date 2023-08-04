package neuro.cryptocurrencies.domain.repository.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface GetTagDetailsRepository {
	/**
	 * Get tag details.
	 * @param tagId tag id.
	 * @return tag details.
	 */
	suspend fun getTagDetails(tagId: String): TagDetails
}