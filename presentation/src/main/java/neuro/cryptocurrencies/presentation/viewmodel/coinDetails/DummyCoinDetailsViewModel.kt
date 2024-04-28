package neuro.cryptocurrencies.presentation.viewmodel.coinDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamMemberModel
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

class DummyCoinDetailsViewModel : CoinDetailsViewModel {
	override val uiState: State<CoinDetailsState> =
		mutableStateOf(
			CoinDetailsState(
				CoinDetailsWithPriceModel(
					CoinDetailsModel(
						"bitcoin",
						"description",
						"SHA256",
						"",
						"Bitcoin",
						true,
						"POW",
						1,
						"BTC",
						listOf(TagModel("1", "tag1"), TagModel("2", "tag2"), TagModel("3", "tag3")),
						listOf(TeamMemberModel("1", "Satoshi Nakamoto", "Founder")),
						"Coin"
					), "$ 25432"
				)
			)
		)

	override val debounceTimer = DebounceTimer()

	override fun errorShown() {

	}

	override fun onRetry() {

	}

	override fun onRefresh() {

	}

	override fun onDialogDismiss() {

	}

	override fun onTagClick(tagModel: TagModel) {

	}

	override fun onTeamMemberClick(teamMemberModel: TeamMemberModel) {

	}
}