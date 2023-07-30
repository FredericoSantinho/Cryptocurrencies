package neuro.cryptocurrencies.presentation.ui.coin.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

@Composable
fun CoinListItemComposable(
	rank: Int,
	name: String,
	symbol: String,
	isActive: Boolean,
	maxLines: Int = 1,
	modifier: Modifier = Modifier
) {
	ConstraintLayout(
		modifier = modifier
			.padding(16.dp)
			.fillMaxWidth()
	) {
		val (textC, activeC) = createRefs()

		Text(
			text = "$rank. $name ($symbol)",
			style = MaterialTheme.typography.h6,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier.constrainAs(textC) {
				start.linkTo(parent.start)
				end.linkTo(activeC.start, margin = 16.dp)
				width = Dimension.fillToConstraints
			},
			maxLines = maxLines
		)
		Text(
			text = if (!isActive) "\"${stringResource(id = R.string.active)}\"" else "\"${
				stringResource(
					id = R.string.inactive
				)
			}\"",
			textAlign = TextAlign.End,
			color = if (isActive) Color.Green else Color.Red,
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
		CoinListItemComposable(1, "Bitcoin", "BTC", true)
	}
}