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

@Composable
fun MainComposable() {
//	CoinListComposable()

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
			route = Screen.CoinDetailScreen.route + "/{coinId}"
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
