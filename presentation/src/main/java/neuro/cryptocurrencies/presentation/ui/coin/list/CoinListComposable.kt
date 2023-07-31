package neuro.cryptocurrencies.presentation.ui.coin.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import kotlinx.coroutines.flow.SharedFlow
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.viewmodel.coins.CoinListViewModel
import neuro.cryptocurrencies.presentation.viewmodel.coins.CoinListViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coins.DummyCoinListViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinListComposable(
	navController: NavHostController,
	viewModel: CoinListViewModel = getViewModel<CoinListViewModelImpl>()
) {
	val coinListState = viewModel.uiState.value

	Scaffold(topBar = { SearchAppBar({ viewModel.onSearchTerm(it) }) }) {
		Surface(
			modifier = Modifier
				.padding(it)
				.fillMaxSize(),
			color = MaterialTheme.colors.background
		) {
			if (coinListState.isLoading) {
				Box(modifier = Modifier.fillMaxSize()) {
					CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}
			} else {
				if (coinListState.isError && !coinListState.isRefreshing) {
					Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
						Column(horizontalAlignment = Alignment.CenterHorizontally) {
							Text(text = stringResource(id = R.string.no_data_available))
							Text(text = stringResource(id = R.string.refresh))
							IconButton(onClick = { viewModel.onRefresh() }) {
								Icon(Icons.Default.Refresh, contentDescription = "refresh")
							}
						}
					}
				} else {
					val pullRefreshState =
						rememberPullRefreshState(coinListState.isRefreshing, { viewModel.onRefresh() })

					Box(
						modifier = Modifier
							.fillMaxSize()
							.pullRefresh(pullRefreshState)
					) {
						PullRefreshIndicator(
							coinListState.isRefreshing,
							pullRefreshState,
							Modifier.align(Alignment.TopCenter)
						)

						coinListState.coins?.let {
							LazyColumn(modifier = Modifier) {
								items(coinListState.coins) {
									CoinListItemComposable(
										it.rank,
										it.name,
										it.symbol,
										it.price,
										modifier = Modifier.clickable { viewModel.onCoinClick(it.id) })
								}
							}
						}
					}
				}

				val context = LocalContext.current
				LaunchedEffect(key1 = coinListState.errorMessage) {
					if (coinListState.errorMessage.isNotBlank() && !coinListState.isRefreshing) {
						Toast.makeText(context, coinListState.errorMessage, Toast.LENGTH_LONG).show()
						viewModel.errorShown()
					}
				}
			}
		}
	}

	onUiEvent(viewModel.uiEvent, navController)
}

@Composable
fun onUiEvent(
	uiEventSharedFlow: SharedFlow<CoinListViewModelImpl.UiEvent>,
	navController: NavHostController
) {
	LaunchedEffect(key1 = Unit) {
		uiEventSharedFlow.collect { uiEvent ->
			when (uiEvent) {
				is CoinListViewModelImpl.UiEvent.NavigateToDetails -> navController.navigate(Screen.CoinDetailScreen.route + "/${uiEvent.coinId}")
			}
		}
	}
}

@Preview
@Composable
fun PreviewCoinListComposable() {
	CryptocurrenciesTheme {
		CoinListComposable(rememberNavController(), DummyCoinListViewModel())
	}
}
