package neuro.cryptocurrencies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.ObserveTagRepository

class ObserveTagRepositoryImpl(private val tagDao: TagDao) : ObserveTagRepository {
	override fun observeTag(tagId: String): Flow<TagDetails> {
		return tagDao.observeTag(tagId).mapNotNull { it?.toDomain() }
	}
}