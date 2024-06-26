package neuro.cryptocurrencies.presentation.ui.screens.coinDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import neuro.cryptocurrencies.R
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.ui.common.composables.AlertDialogDismissable
import neuro.cryptocurrencies.presentation.ui.screens.common.CoinHeaderComposable
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.ui.theme.blackTransparent
import neuro.cryptocurrencies.presentation.utils.compose.snackbar.showSnackBar
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsViewModel
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.DummyCoinDetailsViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreenComposable(
	navController: NavController,
	viewModel: CoinDetailsViewModel = hiltViewModel<CoinDetailsViewModelImpl>()
) {
	val uiState = viewModel.uiState.value
	val coinDetailsModelWithPrice = uiState.coinDetailsWithPriceModel

	val snackState = remember { SnackbarHostState() }
	val snackScope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			TopAppBar(
				navController,
				coinDetailsModelWithPrice?.coinDetailsModel?.name ?: stringResource(id = R.string.app_name)
			)
		},
		snackbarHost = {
			SnackbarHost(hostState = snackState, modifier = Modifier.navigationBarsPadding()) { data ->
				Snackbar(
					backgroundColor = Color.DarkGray,
					snackbarData = data
				)
			}
		},
		modifier = Modifier
			.background(Color.Green)
			.statusBarsPadding()
	) {
		Box(
			modifier = Modifier
				.padding(it)
				.fillMaxSize()
		) {
			Image(
				painter = painterResource(id = R.drawable.coins_background),
				contentDescription = "",
				contentScale = ContentScale.Crop,
				modifier = Modifier.fillMaxSize()
			)
			Column(
				modifier = Modifier
					.fillMaxSize()
					.background(blackTransparent)
			) {
				if (uiState.isLoading) {
					Box(modifier = Modifier.fillMaxSize()) {
						CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
					}
				} else {
					if (uiState.isErrorState) {
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
						coinDetailsModelWithPrice?.let {
							val coinDetailsModel = coinDetailsModelWithPrice.coinDetailsModel
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

								LazyColumn(
									modifier = Modifier.padding(horizontal = 16.dp),
									verticalArrangement = Arrangement.spacedBy(16.dp),
								) {
									coinDetailsModel.logo?.let {
										item {
											Column {
												Spacer(modifier = Modifier.height(16.dp))
												Row(
													modifier = Modifier.fillMaxWidth(),
													horizontalArrangement = Arrangement.Center
												) {
													Image(
														modifier = Modifier
															.size(96.dp),
														painter = rememberAsyncImagePainter(
															coinDetailsModel.logo
														),
														contentDescription = null,
														contentScale = ContentScale.Crop
													)
												}
											}
										}
									}
									item {
										CoinHeaderComposable(
											rank = coinDetailsModel.rank ?: 0,
											name = coinDetailsModel.name ?: "",
											symbol = coinDetailsModel.symbol ?: "",
											price = coinDetailsModelWithPrice.price,
											maxLines = 3,
											textStyle = MaterialTheme.typography.h5,
											modifier = Modifier
										)
									}
									coinDetailsModel.description?.let {
										item {
											Body(coinDetailsModel.description)
										}
									}
									coinDetailsModel.type?.let {
										item {
											Header(stringResource(id = R.string.type))
										}
										item {
											Body(text = coinDetailsModel.type)
										}
									}
									item {
										Header(text = stringResource(id = R.string.open_source))
									}
									item {
										Body(text = coinDetailsModel.openSource.toString())
									}
									if (coinDetailsModel.proofType.isNotBlank()) {
										item {
											Header(text = stringResource(id = R.string.proof_type))
										}
										item {
											Body(text = coinDetailsModel.proofType)
										}
									}
									if (coinDetailsModel.hashAlgorithm.isNotBlank()) {
										item {
											Header(text = stringResource(id = R.string.hash_algorithm))
										}
										item {
											Body(text = coinDetailsModel.hashAlgorithm)
										}
									}
									if (coinDetailsModel.tags.isNotEmpty()) {
										item {
											Header(text = stringResource(id = R.string.tags))
										}
										item {
											FlowRow(
												modifier = Modifier.fillMaxWidth()
											) {
												for (tag in coinDetailsModel.tags) {
													CoinTag(tag.name, modifier = Modifier
														.padding(4.dp)
														.clickable {
															viewModel.onTagClick(tag)
														})
												}
											}
										}
									}
									if (coinDetailsModel.team.isNotEmpty()) {
										item {
											Header(text = stringResource(id = R.string.team_members))
										}
										items(coinDetailsModel.team) { teamMemberModel ->
											TeamMemberComposable(teamMemberModel)
										}
									}
									item {
										Spacer(modifier = Modifier.navigationBarsPadding())
									}
								}
							}
						}
					}
				}
			}
		}

		uiState.dialogTitle?.let {
			AlertDialogDismissable(
				title = uiState.dialogTitle,
				text = uiState.dialogText.toPresentation()
					.ifBlank { stringResource(id = R.string.no_description_available) },
				onDismissRequest = { viewModel.onDialogDismiss() },
				loading = uiState.dialogLoading,
				modifier = Modifier.height(240.dp)
			)
		}
	}

	if (uiState.errorMessage != ErrorMessage.Empty) {
		val errorMessage = uiState.errorMessage.toPresentation()
		LaunchedEffect(key1 = uiState.errorMessage) {
			if (errorMessage.isNotBlank()) {
				showSnackBar(errorMessage, snackScope, snackState)
				viewModel.errorShown()
			}
		}
	}
}

@Composable
private fun Header(text: String) {
	Text(
		text = text,
		style = MaterialTheme.typography.h5
	)
}

@Composable
private fun Body(text: String) {
	Text(
		text = text,
		style = MaterialTheme.typography.body2
	)
}

@Composable
private fun TopAppBar(navController: NavController, title: String) {
	TopAppBar(
		elevation = 4.dp,
		title = {
			Text(
				text = title,
				color = MaterialTheme.colors.background
			)
		},
		navigationIcon = {
			IconButton(onClick = { navController.navigateUp() }) {
				Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.Black)
			}
		},
		backgroundColor = MaterialTheme.colors.primary,
	)
}

@Preview
@Composable
fun PreviewCoinDetailsComposable() {
	CryptocurrenciesTheme {
		CoinDetailsScreenComposable(rememberNavController(), DummyCoinDetailsViewModel())
	}
}