package neuro.coin.paprika.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import neuro.coin.paprika.presentation.ui.coin.CoinDetailsComposable
import neuro.coin.paprika.presentation.ui.coin.list.CoinListComposable
import neuro.coin.paprika.presentation.ui.theme.CoinPaprikaTheme
import neuro.coin.paprika.presentation.viewmodel.coins.details.CoinDetailsViewModel

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
			route = Screen.CoinDetailScreen.route + "/{${CoinDetailsViewModel.PARAM_COIN_ID}}"
		) {
			CoinDetailsComposable()
		}
	}

}

@Preview
@Composable
fun PreviewMainComposable() {
	CoinPaprikaTheme {
		MainComposable()
	}
}
