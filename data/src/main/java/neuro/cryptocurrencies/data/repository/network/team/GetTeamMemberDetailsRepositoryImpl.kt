package neuro.cryptocurrencies.data.repository.network.team

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.data.repository.network.util.makeRequest
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails
import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository

class GetTeamMemberDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetTeamMemberDetailsRepository {
	override suspend fun getTeamMemberDetails(teamMemberId: String): TeamMemberDetails {
		return makeRequest {
			coinPaprikaApi.getTeamMemberDetails(teamMemberId).toDomain()
		}
	}
}