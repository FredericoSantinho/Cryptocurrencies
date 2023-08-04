package neuro.cryptocurrencies.presentation.model

sealed class DialogText {
	data class GivenText(val text: String) : DialogText()
	object Empty : DialogText()
	object UnexpectedError : DialogText()
	object NoDataAvailable : DialogText()
	object ErrorRetrievingData : DialogText()
}