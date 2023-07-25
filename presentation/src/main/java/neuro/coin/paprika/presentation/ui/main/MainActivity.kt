package neuro.coin.paprika.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import neuro.coin.paprika.presentation.ui.theme.CoinPaprikaTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CoinPaprikaTheme {
				MainComposable()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CoinPaprikaTheme {
		MainComposable()
	}
}