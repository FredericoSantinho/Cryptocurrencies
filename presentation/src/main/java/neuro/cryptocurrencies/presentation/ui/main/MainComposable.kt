package neuro.cryptocurrencies.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neuro.cryptocurrencies.presentation.Screen
import neuro.cryptocurrencies.presentation.ui.coin.details.CoinDetailsComposable
import neuro.cryptocurrencies.presentation.ui.coin.list.CoinListComposable
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.CoinDetailsViewModelImpl

@Composable
fun MainComposable() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Screen.CoinListScreen.route
	) {
		composable(
			route = Screen.CoinListScreen.route
		) {
			CoinListComposable(navController)
		}
		composable(
			route = Screen.CoinDetailScreen.route + "/{${CoinDetailsViewModelImpl.PARAM_COIN_ID}}"
		) {
			CoinDetailsComposable(navController)
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
