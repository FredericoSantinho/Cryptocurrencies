package neuro.cryptocurrencies.presentation.ui.base

import androidx.lifecycle.ViewModel
import neuro.cryptocurrencies.presentation.navigation.CallManager
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

open class BaseViewModel : ViewModel() {
	val callManager = CallManager()
	val debounceTimer = DebounceTimer()
}