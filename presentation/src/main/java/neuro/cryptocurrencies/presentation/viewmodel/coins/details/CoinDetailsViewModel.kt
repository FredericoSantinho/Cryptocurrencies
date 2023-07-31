package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import neuro.cryptocurrencies.presentation.model.TeamModel

interface CoinDetailsViewModel {
	val uiState: State<CoinDetailsState>

	fun errorShown()
	fun onRetry()
	fun onRefresh()
	fun onDialogDismiss()
	fun onTagClick(tag: String)
	fun onTeamMemberClick(teamModel: TeamModel)
}