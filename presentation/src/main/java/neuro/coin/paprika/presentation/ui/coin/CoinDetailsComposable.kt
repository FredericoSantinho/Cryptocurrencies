package neuro.coin.paprika.presentation.ui.coin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.TeamListItem
import neuro.coin.paprika.presentation.ui.coin.list.CoinListItemComposable
import neuro.coin.paprika.presentation.viewmodel.coins.details.CoinDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDetailsComposable(coinDetailsViewModel: CoinDetailsViewModel = getViewModel()) {
	val coinDetailsModel = coinDetailsViewModel.uiState.value.coinDetailsModel

	if (coinDetailsModel != null) {
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
			LazyColumn(
				modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp),
			) {
				item {
					CoinListItemComposable(
						coinDetailsModel.rank,
						coinDetailsModel.name,
						coinDetailsModel.symbol,
						coinDetailsModel.isActive
					)
				}
				item {
					Text(
						text = coinDetailsModel.description,
						style = MaterialTheme.typography.body2
					)
				}
				item {
					Text(text = "Type", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.type, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Open Source", style = MaterialTheme.typography.h5)
				}
				item {
					Text(
						text = coinDetailsModel.openSource.toString(),
						style = MaterialTheme.typography.body2
					)
				}
				item {
					Text(text = "Proof type", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.proofType, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Hash Algorithm", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.hashAlgorithm, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Tags", style = MaterialTheme.typography.h5)
				}
				item {
					FlowRow(
						mainAxisSpacing = 10.dp,
						crossAxisSpacing = 10.dp,
						modifier = Modifier.fillMaxWidth()
					) {
						for (tag in coinDetailsModel.tags) {
							CoinTag(tag)
						}
					}
				}
				item {
					Text(text = "Team members", style = MaterialTheme.typography.h5)
				}
				items(coinDetailsModel.teamModel) { teamModel ->
					TeamListItem(teamModel)
					Divider()
				}
			}
		}
	}


	coinDetailsModel?.let { coinDetailsModel ->
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
			LazyColumn(
				modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp),
			) {
				item {
					CoinListItemComposable(
						coinDetailsModel.rank,
						coinDetailsModel.name,
						coinDetailsModel.symbol,
						coinDetailsModel.isActive
					)
				}
				item {
					Text(
						text = coinDetailsModel.description,
						style = MaterialTheme.typography.body2
					)
				}
				item {
					Text(text = "Type", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.type, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Open Source", style = MaterialTheme.typography.h5)
				}
				item {
					Text(
						text = coinDetailsModel.openSource.toString(),
						style = MaterialTheme.typography.body2
					)
				}
				item {
					Text(text = "Proof type", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.proofType, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Hash Algorithm", style = MaterialTheme.typography.h5)
				}
				item {
					Text(text = coinDetailsModel.hashAlgorithm, style = MaterialTheme.typography.body2)
				}
				item {
					Text(text = "Tags", style = MaterialTheme.typography.h5)
				}
				item {
					FlowRow(
						mainAxisSpacing = 10.dp,
						crossAxisSpacing = 10.dp,
						modifier = Modifier.fillMaxWidth()
					) {
						for (tag in coinDetailsModel.tags) {
							CoinTag(tag)
						}
					}
				}
				item {
					Text(text = "Team members", style = MaterialTheme.typography.h5)
				}
				items(coinDetailsModel.teamModel) { teamModel ->
					TeamListItem(teamModel)
					Divider()
				}
			}
		}
	}
}
