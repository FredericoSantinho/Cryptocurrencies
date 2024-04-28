package neuro.cryptocurrencies.presentation.viewmodel.coinDetails

import androidx.compose.runtime.State
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamMemberModel
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

interface CoinDetailsViewModel {
	val uiState: State<CoinDetailsState>

	val debounceTimer: DebounceTimer

	fun errorShown()
	fun onRetry()
	fun onRefresh()
	fun onDialogDismiss()
	fun onTagClick(tagModel: TagModel)
	fun onTeamMemberClick(teamMemberModel: TeamMemberModel)
}