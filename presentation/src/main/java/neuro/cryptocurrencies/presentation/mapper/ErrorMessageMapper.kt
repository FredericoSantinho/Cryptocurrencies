package neuro.cryptocurrencies.presentation.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.model.ErrorMessage

@Composable
fun ErrorMessage.toPresentation(): String =
	when (this) {
		ErrorMessage.Empty -> ""
		is ErrorMessage.GivenMessage -> message
		ErrorMessage.UnexpectedErrorOccurred -> stringResource(id = R.string.unexpected_error)
	}