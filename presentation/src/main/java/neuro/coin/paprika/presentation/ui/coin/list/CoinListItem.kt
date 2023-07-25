package neuro.coin.paprika.presentation.ui.coin.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CoinListItemClickableComposable(isActive: Boolean) {
	Row(modifier = Modifier.clickable { }) {
		CoinListItemComposable(isActive = isActive)
	}
}

@Composable
fun CoinListItemComposable(modifier: Modifier = Modifier, isActive: Boolean) {
	Row(modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
		Text(text = "1. Bitcoin (BTC)", style = MaterialTheme.typography.h6)
		Spacer(modifier = Modifier.weight(1.0f))
		Text(
			text = if (isActive) "Active" else "Inactive",
			color = if (isActive) Color.Green else Color.Red
		)
	}
}