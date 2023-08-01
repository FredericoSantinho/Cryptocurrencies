package neuro.cryptocurrencies.data.repository.tag

import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.SaveTagDetailsRepository

class SaveTagDetailsRepositoryImpl(private val tagDetailsDao: TagDetailsDao) :
	SaveTagDetailsRepository {
	override suspend fun saveTagDetails(tagDetails: TagDetails) {
		tagDetailsDao.upsertTagDetails(tagDetails.toDatabase())
	}
}