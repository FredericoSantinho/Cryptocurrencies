package neuro.cryptocurrencies.data.repository.database.team

import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.domain.repository.team.HasCachedTeamMemberDetailsRepository

class HasCachedTeamMemberDetailsRepositoryImpl(private val teamDetailsDao: TeamDetailsDao) :
	HasCachedTeamMemberDetailsRepository {
	override suspend fun hasCachedTeamMemberDetails(teamMemberId: String): Boolean {
		return teamDetailsDao.hasTeamMemberDetails(teamMemberId)
	}
}