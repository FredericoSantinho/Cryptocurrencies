package neuro.expenses.register.viewmodel.common.formatter

interface CurrencyFormatter {
	fun format(value: Double): String
	fun format(string: String): String
}