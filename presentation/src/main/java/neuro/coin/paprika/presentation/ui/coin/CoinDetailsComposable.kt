package neuro.coin.paprika.presentation.ui.coin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.TeamListItem
import neuro.coin.paprika.presentation.ui.model.TeamMember

@Composable
fun CoinDetailsComposable() {
	Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
		LazyColumn(
			modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			item {
				// TODO: Uncomment with proper parameters
				//				CoinListItemComposable()
			}
			item {
				Text(
					text = "Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.",
					style = MaterialTheme.typography.body2
				)
			}
			item {
				Text(text = "Type", style = MaterialTheme.typography.h5)
			}
			item {
				Text(text = "Coin", style = MaterialTheme.typography.body2)
			}
			item {
				Text(text = "Open Source", style = MaterialTheme.typography.h5)
			}
			item {
				Text(text = "True", style = MaterialTheme.typography.body2)
			}
			item {
				Text(text = "Proof type", style = MaterialTheme.typography.h5)
			}
			item {
				Text(text = "Proof of work", style = MaterialTheme.typography.body2)
			}
			item {
				Text(text = "Hash Algorithm", style = MaterialTheme.typography.h5)
			}
			item {
				Text(text = "SHA256", style = MaterialTheme.typography.body2)
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
					for (i in 0 until 10) {
						CoinTag("Test Tag")
					}
				}
			}
			item {
				Text(text = "Team members", style = MaterialTheme.typography.h5)
			}
			item {
				TeamListItem(teamMember = TeamMember("1", "Satoshi", "Lead"))
				Divider()
				TeamListItem(teamMember = TeamMember("2", "Friend 1", "Developer"))
				Divider()
				TeamListItem(teamMember = TeamMember("3", "Friend 2", "Developer"))
				Divider()
			}
		}

//		Column {
//			CoinListItemComposable()
//			CoinListItemComposable()
//			CoinListItemComposable()
//		}
	}
}
