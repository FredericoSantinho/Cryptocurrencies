package neuro.cryptocurrencies.presentation.model

sealed class ErrorMessage {
	data class GivenMessage(val message: String) : ErrorMessage()
	data object UnexpectedErrorOccurred : ErrorMessage()
	data object Empty : ErrorMessage()
}