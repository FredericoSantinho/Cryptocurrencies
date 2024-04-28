package neuro.cryptocurrencies.presentation.ui.common.formatter

interface CurrencyFormatter {
	fun format(value: Double): String
	fun format(string: String): String
}