package neuro.cryptocurrencies.presentation.ui.base

import androidx.lifecycle.ViewModel
import neuro.cryptocurrencies.presentation.utils.helper.DebounceTimer

open class BaseViewModel : ViewModel() {
	val debounceTimer = DebounceTimer()
}