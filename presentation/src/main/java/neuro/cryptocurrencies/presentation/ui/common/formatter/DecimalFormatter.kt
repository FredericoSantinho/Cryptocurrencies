package neuro.cryptocurrencies.presentation.ui.common.formatter

fun interface DecimalFormatter {
	fun format(value: Double): String
}