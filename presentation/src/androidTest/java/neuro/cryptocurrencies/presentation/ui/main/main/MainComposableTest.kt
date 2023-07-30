package neuro.cryptocurrencies.presentation.ui.main.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import neuro.cryptocurrencies.presentation.ui.main.MainComposable
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import org.junit.Rule
import org.junit.Test

class MainComposableTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private val helloAndroidNode = composeTestRule.onNodeWithTag("Android")

	@Test
	fun myTest() {
		// Start the app
		composeTestRule.setContent {
			CryptocurrenciesTheme {
				MainComposable()
			}
		}

		helloAndroidNode.assertExists()
		helloAndroidNode.assertIsDisplayed()
		helloAndroidNode.assertTextEquals("Hello Android!")
	}

}