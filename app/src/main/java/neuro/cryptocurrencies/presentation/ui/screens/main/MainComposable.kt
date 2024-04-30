package neuro.cryptocurrencies.presentation.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neuro.cryptocurrencies.presentation.ui.screens.ScreenRoutes
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsViewModelImpl

@Composable
fun MainComposable() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = ScreenRoutes.coinList
	) {
		composable(
			route = ScreenRoutes.coinList
		) {
			neuro.cryptocurrencies.presentation.ui.screens.coinList.CoinListScreenComposable(navController)
		}
		composable(
			route = ScreenRoutes.coinDetails + "/{${CoinDetailsViewModelImpl.PARAM_COIN_ID}}"
		) {
			neuro.cryptocurrencies.presentation.ui.screens.coinDetails.CoinDetailsScreenComposable(
				navController
			)
		}
	}

}

@Preview
@Composable
fun PreviewMainComposable() {
	CryptocurrenciesTheme {
		MainComposable()
	}
}
