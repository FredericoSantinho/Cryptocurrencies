package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.TeamModel

class DummyCoinDetailsViewModel : CoinDetailsViewModel {
	override val uiState: State<CoinDetailsState> =
		mutableStateOf(
			CoinDetailsState(
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
					listOf("tag1","tag2","tag3"),
					listOf(TeamModel("Satoshi Nakamoto", "Founder")),
					"Coin"
				)
			)
		)
}