package neuro.cryptocurrencies.data.repository.database.team

import neuro.cryptocurrencies.data.dao.TeamDetailsDao
import neuro.cryptocurrencies.data.mapper.database.toDatabase
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails
import neuro.cryptocurrencies.domain.repository.team.SaveTeamMemberDetailsRepository

class SaveTeamMemberDetailsRepositoryImpl(private val teamDetailsDao: TeamDetailsDao) :
	SaveTeamMemberDetailsRepository {
	override suspend fun saveTeamMemberDetails(teamMemberDetails: TeamMemberDetails) {
		teamDetailsDao.upsertTeamMemberDetails(teamMemberDetails.toDatabase())
	}
}