package neuro.cryptocurrencies.data.repository.database.team

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDomain
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails
import neuro.cryptocurrencies.domain.repository.team.ObserveTeamMemberDetailsRepository

class ObserveTeamMemberDetailsRepositoryImpl(private val teamDetailsDao: TeamDetailsDao) :
	ObserveTeamMemberDetailsRepository {
	override fun observeTeamMemberDetails(teamMemberId: String): Flow<TeamMemberDetails> {
		return teamDetailsDao.observeTeamMemberDetails(teamMemberId).mapNotNull { it?.toDomain() }
	}
}