package neuro.cryptocurrencies.presentation.utils.compose.snackbar

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun showSnackBar(
	text: String, coroutineScope: CoroutineScope, snackbarHostState: SnackbarHostState
) {
	coroutineScope.launch { snackbarHostState.showSnackbar(text, duration = SnackbarDuration.Long) }
}
