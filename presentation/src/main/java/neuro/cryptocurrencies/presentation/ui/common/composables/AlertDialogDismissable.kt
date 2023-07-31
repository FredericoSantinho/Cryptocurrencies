package neuro.cryptocurrencies.presentation.ui.common.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
	val openDialog = remember { mutableStateOf(true) }

	if (openDialog.value) {

		AlertDialog(
			modifier = modifier,
			onDismissRequest = {
				openDialog.value = false
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
					Text(text)
				}
			},
			confirmButton = {
				if (confirmButtonText.isNotEmpty()) {
					Button(
						onClick = {
							openDialog.value = false
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
							openDialog.value = false
							onDismissRequest()
						}) {
						Text(dismissButtonText)
					}
				}
			},
			backgroundColor = MaterialTheme.colors.background
		)
	}
}
