package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamModel

interface CoinDetailsViewModel {
	val uiState: State<CoinDetailsState>

	fun errorShown()
	fun onRetry()
	fun onRefresh()
	fun onDialogDismiss()
	fun onTagClick(tagModel: TagModel)
	fun onTeamMemberClick(teamModel: TeamModel)
}