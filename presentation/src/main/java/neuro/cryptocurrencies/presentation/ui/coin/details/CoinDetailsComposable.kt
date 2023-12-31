package neuro.cryptocurrencies.presentation.ui.coin.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.ui.coin.list.CoinListItemComposable
import neuro.cryptocurrencies.presentation.ui.common.composables.AlertDialogDismissable
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.CoinDetailsViewModel
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.CoinDetailsViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.DummyCoinDetailsViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinDetailsComposable(
	navController: NavController,
	viewModel: CoinDetailsViewModel = getViewModel<CoinDetailsViewModelImpl>()
) {
	val uiState = viewModel.uiState.value
	val coinDetailsModelWithPrice = uiState.coinDetailsWithPriceModel

	Scaffold(topBar = {
		TopAppBar(
			navController,
			coinDetailsModelWithPrice?.coinDetailsModel?.name ?: stringResource(id = R.string.app_name)
		)
	}) {
		Surface(
			modifier = Modifier
				.padding(it)
				.fillMaxSize(),
			color = MaterialTheme.colors.background
		) {
			if (uiState.isLoading) {
				Box(modifier = Modifier.fillMaxSize()) {
					CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}
			} else {
				if (uiState.isError) {
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
								modifier = Modifier.padding(16.dp),
								verticalArrangement = Arrangement.spacedBy(16.dp),
							) {
								if (coinDetailsModel.logo.isNotBlank()) {
									item {
										Row(
											modifier = Modifier.fillMaxWidth(),
											horizontalArrangement = Arrangement.Center
										) {
											Image(
												modifier = Modifier
													.size(64.dp),
												painter = rememberAsyncImagePainter(
													coinDetailsModel.logo
												),
												contentDescription = null,
												contentScale = ContentScale.Crop
											)
										}
									}
								}
								item {
									CoinListItemComposable(
										coinDetailsModel.rank,
										coinDetailsModel.name,
										coinDetailsModel.symbol,
										coinDetailsModelWithPrice.price,
										3
									)
								}
								if (coinDetailsModel.description.isNotBlank()) {
									item {
										Text(
											text = coinDetailsModel.description,
											style = MaterialTheme.typography.body2
										)
									}
								}
								if (coinDetailsModel.type.isNotBlank()) {
									item {
										Text(
											text = stringResource(id = R.string.type),
											style = MaterialTheme.typography.h5
										)
									}
									item {
										Text(text = coinDetailsModel.type, style = MaterialTheme.typography.body2)
									}
								}
								item {
									Text(
										text = stringResource(id = R.string.open_source),
										style = MaterialTheme.typography.h5
									)
								}
								item {
									Text(
										text = coinDetailsModel.openSource.toString(),
										style = MaterialTheme.typography.body2
									)
								}
								if (coinDetailsModel.proofType.isNotBlank()) {
									item {
										Text(
											text = stringResource(id = R.string.proof_type),
											style = MaterialTheme.typography.h5
										)
									}
									item {
										Text(text = coinDetailsModel.proofType, style = MaterialTheme.typography.body2)
									}
								}
								if (coinDetailsModel.hashAlgorithm.isNotBlank()) {
									item {
										Text(
											text = stringResource(id = R.string.hash_algorithm),
											style = MaterialTheme.typography.h5
										)
									}
									item {
										Text(
											text = coinDetailsModel.hashAlgorithm,
											style = MaterialTheme.typography.body2
										)
									}
								}
								if (coinDetailsModel.tags.isNotEmpty()) {
									item {
										Text(
											text = stringResource(id = R.string.tags),
											style = MaterialTheme.typography.h5
										)
									}
									item {
										FlowRow(
											mainAxisSpacing = 10.dp,
											crossAxisSpacing = 10.dp,
											modifier = Modifier.fillMaxWidth()
										) {
											for (tag in coinDetailsModel.tags) {
												CoinTag(tag.name, modifier = Modifier.clickable {
													viewModel.onTagClick(tag)
												})
											}
										}
									}
								}
								if (coinDetailsModel.team.isNotEmpty()) {
									item {
										Text(
											text = stringResource(id = R.string.team_members),
											style = MaterialTheme.typography.h5
										)
									}
									items(coinDetailsModel.team) { teamModel ->
										TeamListItem(teamModel, modifier = Modifier.clickable {
											viewModel.onTeamMemberClick(teamModel)
										})
										Divider()
									}
								}
							}
						}
					}
				}
			}
		}

		if (uiState.showDialog) {
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

	val context = LocalContext.current
	val errorMessage = uiState.errorMessage.toPresentation()
	LaunchedEffect(key1 = uiState.errorMessage) {
		if (errorMessage.isNotBlank()) {
			Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
			viewModel.errorShown()
		}
	}

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
				Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
			}
		},
		backgroundColor = MaterialTheme.colors.primary,
	)
}

@Preview
@Composable
fun PreviewCoinDetailsComposable() {
	CryptocurrenciesTheme {
		CoinDetailsComposable(rememberNavController(), DummyCoinDetailsViewModel())
	}
}