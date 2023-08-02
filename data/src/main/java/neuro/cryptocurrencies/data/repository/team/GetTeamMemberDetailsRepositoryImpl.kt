package neuro.cryptocurrencies.data.repository.team

import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.mapper.network.toDomain
import neuro.cryptocurrencies.domain.entity.TeamMemberDetails
import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.usecase.error.ErrorRetrievingDataException
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import retrofit2.HttpException
import java.io.IOException

class GetTeamMemberDetailsRepositoryImpl(private val coinPaprikaApi: CoinPaprikaApi) :
	GetTeamMemberDetailsRepository {
	override suspend fun getTeamMemberDetails(teamMemberId: String): TeamMemberDetails {
		try {
			return coinPaprikaApi.getTeamMember(teamMemberId).toDomain()
		} catch (e: HttpException) {
			if (e.code() == 404) {
				throw NoDataAvailableException(e.localizedMessage ?: "Not found")
			} else {
				throw ErrorRetrievingDataException(e.localizedMessage ?: "An unexpected error occurred")
			}
		} catch (e: IOException) {
			throw ErrorRetrievingDataException("Couldn't reach server. Check your internet connection.")
		}
	}
}