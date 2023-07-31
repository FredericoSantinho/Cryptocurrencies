package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.cryptocurrencies.presentation.model.TeamModel

class DummyCoinDetailsViewModel : CoinDetailsViewModel {
	override val uiState: State<CoinDetailsState> =
		mutableStateOf(
			CoinDetailsState(
				CoinDetailsWithPriceModel(
					CoinDetailsModel(
						"description",
						"SHA256",
						"bitcoin",
						true,
						"",
						"Bitcoin",
						true,
						"POW",
						1,
						"BTC",
						listOf("tag1", "tag2", "tag3"),
						listOf(TeamModel("Satoshi Nakamoto", "Founder")),
						"Coin"
					), "$ 25432"
				)
			)
		)

	override fun errorShown() {

	}

	override fun onRetry() {

	}

	override fun onRefresh() {

	}

	override fun onDialogDismiss() {

	}

	override fun onTagClick(tag: String) {

	}
}