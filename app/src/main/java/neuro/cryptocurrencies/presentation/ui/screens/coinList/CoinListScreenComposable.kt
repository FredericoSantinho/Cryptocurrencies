package neuro.cryptocurrencies.presentation.ui.screens.coinList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.SharedFlow
import neuro.cryptocurrencies.R
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.ui.screens.ScreenRoutes
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.ui.theme.blackTransparent
import neuro.cryptocurrencies.presentation.utils.compose.snackbar.showSnackBar
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer
import neuro.cryptocurrencies.presentation.viewmodel.coinList.CoinListViewModel
import neuro.cryptocurrencies.presentation.viewmodel.coinList.CoinListViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coinList.DummyCoinListViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinListScreenComposable(
	navController: NavHostController,
	viewModel: CoinListViewModel = hiltViewModel<CoinListViewModelImpl>()
) {
	val uiState = viewModel.uiState.value

	val snackState = remember { SnackbarHostState() }
	val snackScope = rememberCoroutineScope()

	Scaffold(
		topBar = { SearchAppBar({ viewModel.onSearchTerm(it) }) },
		snackbarHost = {
			SnackbarHost(hostState = snackState, modifier = Modifier.navigationBarsPadding()) { data ->
				Snackbar(
					backgroundColor = Color.DarkGray,
					snackbarData = data
				)
			}
		},
		modifier = Modifier
			.background(MaterialTheme.colors.primary)
			.statusBarsPadding()
	) {
		Surface(
			modifier = Modifier
				.padding(it)
				.fillMaxSize()
		) {
			Image(
				painter = painterResource(id = R.drawable.coins_background),
				contentDescription = "",
				contentScale = ContentScale.Crop
			)
			if (uiState.isLoading) {
				Box(modifier = Modifier.fillMaxSize()) {
					CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}
			} else {
				if (uiState.isErrorState && !uiState.isRefreshing) {
					Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
						Column(horizontalAlignment = Alignment.CenterHorizontally) {
							Text(text = stringResource(id = R.string.no_data_available))
							Text(text = stringResource(id = R.string.refresh))
							IconButton(onClick = { viewModel.onRetry() }) {
								Icon(Icons.Default.Refresh, contentDescription = "refresh")
							}
						}
					}
				} else {
					val pullRefreshState =
						rememberPullRefreshState(uiState.isRefreshing, { viewModel.onRefresh() })

					Box(
						modifier = Modifier
							.fillMaxSize()
							.pullRefresh(pullRefreshState)
					) {
						PullRefreshIndicator(
							uiState.isRefreshing,
							pullRefreshState,
							Modifier.align(Alignment.TopCenter)
						)

						uiState.coins?.let {
							LazyColumn(
								modifier = Modifier
									.clip(MaterialTheme.shapes.large)
									.background(blackTransparent)
							) {
								items(uiState.coins, { item -> item.id }) {
									CoinListItemComposable(
										it.rank,
										it.name,
										it.symbol,
										it.price,
										modifier = Modifier.clickable { viewModel.onCoinClick(it.id) })
								}
								item {
									Spacer(modifier = Modifier.navigationBarsPadding())
								}
							}
						}
					}
				}

				if (uiState.errorMessage != ErrorMessage.Empty) {
					val errorMessage = uiState.errorMessage.toPresentation()
					LaunchedEffect(key1 = errorMessage) {
						if (errorMessage.isNotBlank() && !uiState.isRefreshing) {
							showSnackBar(errorMessage, snackScope, snackState)
							viewModel.errorShown()
						}
					}
				}
			}
		}
	}

	onUiEvent(viewModel.uiEvent, navController, viewModel.debounceTimer)
}

@Composable
private fun onUiEvent(
	uiEventSharedFlow: SharedFlow<CoinListViewModelImpl.UiEvent>,
	navController: NavHostController,
	debounceTimer: DebounceTimer
) {
	LaunchedEffect(key1 = Unit) {
		uiEventSharedFlow.collect { uiEvent ->
			debounceTimer.debounceRunFirst {
				when (uiEvent) {
					is CoinListViewModelImpl.UiEvent.NavigateToDetails -> navController.navigate(ScreenRoutes.coinDetails + "/${uiEvent.coinId}")
				}
			}
		}
	}
}

@Preview
@Composable
fun PreviewCoinListComposable() {
	CryptocurrenciesTheme {
		CoinListScreenComposable(rememberNavController(), DummyCoinListViewModel())
	}
}
