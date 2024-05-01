package neuro.cryptocurrencies.data.repository.database.tag

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository
import javax.inject.Inject

class ObserveTagDetailsRepositoryImpl @Inject constructor(private val tagDetailsDao: TagDetailsDao) :
	ObserveTagDetailsRepository {
	override fun observeTagDetails(tagId: String): Flow<TagDetails> {
		return tagDetailsDao.observeTagDetails(tagId).mapNotNull { it?.toDomain() }
			.distinctUntilChanged()
	}
}