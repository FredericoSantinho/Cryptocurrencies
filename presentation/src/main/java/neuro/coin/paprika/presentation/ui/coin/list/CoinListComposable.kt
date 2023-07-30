package neuro.coin.paprika.presentation.ui.coin.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import neuro.coin.paprika.presentation.ui.theme.CoinPaprikaTheme
import neuro.coin.paprika.presentation.viewmodel.coins.CoinListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinListComposable(
	navController: NavHostController,
	coinListViewModel: CoinListViewModel = getViewModel()
) {
	val coinListState = coinListViewModel.uiState.value

	Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
		if (coinListState.isLoading) {
			Box(modifier = Modifier.fillMaxSize()) {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
		} else {
			LazyColumn(modifier = Modifier) {
				items(coinListState.coins) {
					CoinListItemComposable(
						it.rank,
						it.name,
						it.symbol,
						it.isActive,
						modifier = Modifier.clickable { coinListViewModel.onCoinClick(it.id) })
				}
			}

			if (!coinListState.error.isBlank()) {
				Toast.makeText(LocalContext.current, coinListState.error, Toast.LENGTH_LONG).show()
			}
		}
	}

	onUiEvent(coinListViewModel.uiEvent.value, coinListViewModel, navController)
}

fun onUiEvent(
	uiEvent: CoinListViewModel.UiEvent?,
	coinListViewModel: CoinListViewModel,
	navController: NavHostController
) {
	when (uiEvent) {
		is CoinListViewModel.UiEvent.NavigateToDetails -> navController.navigate(Screen.CoinDetailScreen.route + "/${uiEvent.coinId}")
		null -> {}
	}
	coinListViewModel.eventConsumed()
}

@Preview
@Composable
fun PreviewCoinListComposable() {
	CoinPaprikaTheme {
		// TODO: Replace with interface
		//		CoinListComposable(rememberNavController())
	}
}
