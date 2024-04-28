package neuro.cryptocurrencies.presentation.ui.common.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AlertDialogDismissable(
	title: String,
	text: String = "",
	confirmButtonText: String = "",
	onConfirmButton: () -> Unit = {},
	dismissButtonText: String = "",
	onDismissRequest: () -> Unit = {},
	loading: Boolean = false,
	modifier: Modifier = Modifier
) {
	AlertDialog(
		modifier = modifier,
		onDismissRequest = {
			onDismissRequest()
		},
		title = {
			Text(text = title)
		},
		text = {
			if (loading) {
				Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
					CircularProgressIndicator()
				}
			} else {
				Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
					Text(text)
				}
			}
		},
		confirmButton = {
			if (confirmButtonText.isNotEmpty()) {
				Button(
					onClick = {
						onConfirmButton()
					}) {
					Text(confirmButtonText)
				}
			}
		},
		dismissButton = {
			if (dismissButtonText.isNotEmpty()) {
				Button(
					onClick = {
						onDismissRequest()
					}) {
					Text(dismissButtonText)
				}
			}
		},
		backgroundColor = MaterialTheme.colors.background
	)
}
