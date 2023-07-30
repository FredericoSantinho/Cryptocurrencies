package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl

private val currencyFormatter = CurrencyFormatterImpl(DecimalFormatterImpl(2), "$")

fun CoinTicker.toPresentation() = CoinTickerModel(
	id,
	name,
	rank,
	symbol,
	currencyFormatter.format(price),
	percentChange24h.toString()
)

fun List<CoinTicker>.toPresentation() = map { it.toPresentation() }