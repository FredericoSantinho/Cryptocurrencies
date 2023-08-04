package neuro.cryptocurrencies.domain.usecase.error

/**
 * Exception thrown when there is an error retrieving the data.
 */
class ErrorRetrievingDataException(message: String) : Exception(message)