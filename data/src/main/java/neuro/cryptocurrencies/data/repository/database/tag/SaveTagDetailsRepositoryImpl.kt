package neuro.cryptocurrencies.data.repository.database.tag

import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.SaveTagDetailsRepository
import javax.inject.Inject

class SaveTagDetailsRepositoryImpl @Inject constructor(private val tagDetailsDao: TagDetailsDao) :
	SaveTagDetailsRepository {
	override suspend fun saveTagDetails(tagDetails: TagDetails) {
		tagDetailsDao.upsertTagDetails(tagDetails.toDatabase())
	}
}