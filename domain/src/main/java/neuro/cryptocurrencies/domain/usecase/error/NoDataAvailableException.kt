package neuro.cryptocurrencies.domain.usecase.error

/**
 * Exception thrown when the data is not available.
 */
class NoDataAvailableException(message: String) : Exception(message)