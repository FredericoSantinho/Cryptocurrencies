package neuro.cryptocurrencies.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = Color.Green,
	primaryVariant = Color.Green,
	secondary = Teal200,
	background = Color.Black,
	onBackground = Color.White,
	onSurface = Color.White
)

private val LightColorPalette = lightColors(
	primary = Color.Green,
	primaryVariant = Color.Green,
	secondary = Teal200,
	background = Color.Black,
	onBackground = Color.White,
	onSurface = Color.White

	/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CryptocurrenciesTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors = LightColorPalette

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}