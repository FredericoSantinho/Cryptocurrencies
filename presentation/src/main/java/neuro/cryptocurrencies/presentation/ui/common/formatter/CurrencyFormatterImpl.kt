package neuro.cryptocurrencies.presentation.ui.common.formatter

class CurrencyFormatterImpl(
	private val decimalFormatter: DecimalFormatter,
	private val currency: String,
) : CurrencyFormatter {
	override fun format(value: Double): String {
		return currencyPrefix() + decimalFormatter.format(value)
	}

	override fun format(string: String): String {
		return currencyPrefix() + string
	}

	private fun currencyPrefix() = "$currency "
}