package neuro.coin.paprika.presentation.ui.main.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import neuro.coin.paprika.presentation.ui.main.MainComposable
import neuro.coin.paprika.presentation.ui.theme.CoinPaprikaTheme
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
			CoinPaprikaTheme {
				MainComposable()
			}
		}

		helloAndroidNode.assertExists()
		helloAndroidNode.assertIsDisplayed()
		helloAndroidNode.assertTextEquals("Hello Android!")
	}

}