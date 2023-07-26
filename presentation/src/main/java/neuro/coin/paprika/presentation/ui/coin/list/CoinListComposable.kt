package neuro.coin.paprika.presentation.ui.coin.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
		if (coinListState.error.isBlank()) {
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
		} else {
			Column {
				Spacer(modifier = Modifier.weight(1.0f))
				Text(
					text = coinListState.error,
					textAlign = TextAlign.Center,
					modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.weight(1.0f))
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
