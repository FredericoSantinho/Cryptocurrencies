package neuro.cryptocurrencies.domain.repository.tag

import neuro.cryptocurrencies.domain.entity.TagDetails

interface SaveTagDetailsRepository {
	suspend fun saveTagDetails(tagDetails: TagDetails)
}