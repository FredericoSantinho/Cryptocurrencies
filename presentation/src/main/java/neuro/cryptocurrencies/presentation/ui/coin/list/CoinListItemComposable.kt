package neuro.cryptocurrencies.presentation.ui.coin.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import neuro.cryptocurrencies.presentation.R

@Composable
fun CoinListItemComposable(
	rank: Int, name: String, symbol: String, isActive: Boolean, modifier: Modifier = Modifier
) {
	Row(modifier = modifier) {
		Row(modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
			Text(text = "$rank. $name ($symbol)", style = MaterialTheme.typography.h6)
			Spacer(modifier = Modifier.weight(1.0f))
			Text(
				text = if (isActive) "\"${stringResource(id = R.string.active)}\"" else "\"${
					stringResource(
						id = R.string.inactive
					)
				}\"",
				color = if (isActive) Color.Green else Color.Red
			)
		}
	}
}