package neuro.coin.paprika.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

@Composable
fun MainComposable() {
	// A surface container using the 'background' color from the theme
	Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
		Greeting("Android")
	}
}

@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!", modifier = Modifier.semantics { testTag = "Android" })
}