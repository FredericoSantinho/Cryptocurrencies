package neuro.cryptocurrencies.presentation.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			CryptocurrenciesTheme {
				MainComposable()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CryptocurrenciesTheme {
		MainComposable()
	}
}