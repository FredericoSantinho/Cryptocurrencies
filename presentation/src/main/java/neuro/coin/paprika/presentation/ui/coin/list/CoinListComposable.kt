package neuro.coin.paprika.presentation.ui.coin.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import neuro.coin.paprika.presentation.ui.theme.CoinPaprikaTheme

@Composable
fun CoinListComposable(navController: NavHostController) {
	Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
		LazyColumn(modifier = Modifier) {
			items(listOf(1, 2, 3)) {
				CoinListItemClickableComposable(true)
			}
		}
	}
}

@Preview
@Composable
fun PreviewCoinListComposable() {
	CoinPaprikaTheme {
		CoinListComposable(rememberNavController())
	}
}
