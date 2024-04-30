package neuro.cryptocurrencies.presentation.ui.screens.common

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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
	ConstraintLayout(
		modifier = modifier
			.fillMaxWidth()
	) {
		val (textC, activeC) = createRefs()

		Text(
			text = "$rank. $name ($symbol)",
			style = textStyle,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier.constrainAs(textC) {
				start.linkTo(parent.start)
				end.linkTo(activeC.start, margin = 16.dp)
				width = Dimension.fillToConstraints
			},
			maxLines = maxLines
		)
		Text(
			text = price,
			style = textStyle,
			textAlign = TextAlign.End,
			color = Color.Green,
			modifier = Modifier.constrainAs(activeC) {
				end.linkTo(parent.end)
				top.linkTo(parent.top)
				bottom.linkTo(parent.bottom)
			}
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