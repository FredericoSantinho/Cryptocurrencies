package neuro.cryptocurrencies.presentation.ui.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

@Composable
fun CoinHeaderComposable(
	rank: Int,
	name: String,
	symbol: String,
	price: String,
	modifier: Modifier = Modifier,
	maxLines: Int = 1,
	textStyle: TextStyle = MaterialTheme.typography.h6
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
	) {
		Text(
			text = "$rank. $name ($symbol)",
			style = textStyle,
			overflow = TextOverflow.Ellipsis,
			maxLines = maxLines,
			modifier = Modifier.padding(end = 16.dp)
		)
		Spacer(modifier = Modifier.weight(1.0f))
		Text(
			text = price,
			style = textStyle,
			textAlign = TextAlign.End,
			color = Color.Green,
		)
	}
}

@Preview
@Composable
fun PreviewCoinListItemComposable() {
	CryptocurrenciesTheme {
		CoinHeaderComposable(1, "Bitcoin", "BTC", "$ 25342", modifier = Modifier.padding(16.dp))
	}
}