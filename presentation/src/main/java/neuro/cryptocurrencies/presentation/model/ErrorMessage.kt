package neuro.cryptocurrencies.presentation.model

sealed class ErrorMessage {
	data class GivenMessage(val message: String) : ErrorMessage()
	object UnexpectedErrorOccurred : ErrorMessage()
	object Empty : ErrorMessage()
}