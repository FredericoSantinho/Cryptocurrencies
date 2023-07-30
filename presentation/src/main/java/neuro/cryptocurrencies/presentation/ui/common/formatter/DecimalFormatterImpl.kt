package neuro.expenses.register.viewmodel.common.formatter

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class DecimalFormatterImpl(decimals: Int) : DecimalFormatter {
	private val decimalFormat: DecimalFormat

	init {
		require((decimals > 0)) { "Decimals must be greater than zero!" }
		val pattern = StringBuilder("0.")
		for (i in 0 until decimals) {
			pattern.append('0')
		}
		val symbols = DecimalFormatSymbols(Locale.US)
		this.decimalFormat = DecimalFormat(pattern.toString(), symbols)
		decimalFormat.roundingMode = RoundingMode.HALF_UP
	}

	override fun format(value: Double): String {
		return decimalFormat.format(value)
	}
}