package neuro.cryptocurrencies.presentation.navigation

import androidx.navigation.NavController
import neuro.cryptocurrencies.presentation.ui.screens.ScreenRoutes
import javax.inject.Inject

class CallManager @Inject constructor() {

	private fun navigateTo(navController: NavController, route: String) {
		navController.navigate(route = route)
	}

	//APP NAVIGATION/CALLS
	fun coinDetails(navController: NavController, coinId: String) {
		navigateTo(navController, ScreenRoutes.coinDetails + "/${coinId}")
	}
}
