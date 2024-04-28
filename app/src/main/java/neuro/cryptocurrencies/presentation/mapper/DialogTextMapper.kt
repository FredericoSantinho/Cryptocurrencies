package neuro.cryptocurrencies.presentation.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import neuro.cryptocurrencies.R
import neuro.cryptocurrencies.presentation.model.DialogText

@Composable
fun DialogText.toPresentation(): String =
	when (this) {
		DialogText.Empty -> ""
		DialogText.ErrorRetrievingData -> stringResource(id = R.string.error_retrieving_data)
		is DialogText.GivenText -> text
		DialogText.NoDataAvailable -> stringResource(id = R.string.no_data_available)
		DialogText.UnexpectedError -> stringResource(id = R.string.unexpected_error)
	}