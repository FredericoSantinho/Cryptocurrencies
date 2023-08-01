package neuro.cryptocurrencies.data.repository.tag

import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository

class GetTagDetailsRepositoryImpl(private val tagDetailsDao: TagDetailsDao) :
	GetTagDetailsRepository {
	override suspend fun getTagDetails(tagId: String): TagDetails? {
		return tagDetailsDao.getTagDetails(tagId)?.toDomain()
	}
}