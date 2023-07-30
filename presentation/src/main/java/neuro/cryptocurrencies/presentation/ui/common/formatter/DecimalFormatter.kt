package neuro.expenses.register.viewmodel.common.formatter

fun interface DecimalFormatter {
	fun format(value: Double): String
}