package neuro.cryptocurrencies.data.repository.database.tag

import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository
import javax.inject.Inject

class HasCachedTagDetailsRepositoryImpl @Inject constructor(private val tagDetailsDao: TagDetailsDao) :
	HasCachedTagDetailsRepository {
	override suspend fun hasCachedTagDetails(tagId: String): Boolean {
		return tagDetailsDao.hasTagDetails(tagId)
	}
}