package neuro.cryptocurrencies.presentation.model

sealed class DialogText {
	data class GivenText(val text: String) : DialogText()
	data object Empty : DialogText()
	data object UnexpectedError : DialogText()
	data object NoDataAvailable : DialogText()
	data object ErrorRetrievingData : DialogText()
}