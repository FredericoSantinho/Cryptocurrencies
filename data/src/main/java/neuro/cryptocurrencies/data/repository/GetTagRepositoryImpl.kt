package neuro.cryptocurrencies.data.repository

import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.GetTagRepository

class GetTagRepositoryImpl(private val tagDao: TagDao) : GetTagRepository {
	override fun getTag(tagId: String): TagDetails? {
		return tagDao.getTag(tagId)?.toDomain()
	}
}