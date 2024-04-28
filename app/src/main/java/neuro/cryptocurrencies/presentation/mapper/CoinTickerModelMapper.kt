package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinTicker
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.cryptocurrencies.presentation.ui.common.formatter.CurrencyFormatterImpl
import neuro.cryptocurrencies.presentation.ui.common.formatter.DecimalFormatterImpl

private val currencyFormatter = CurrencyFormatterImpl(DecimalFormatterImpl(2), "$")

fun CoinTicker.toPresentation() = CoinTickerModel(
	id,
	name,
	rank,
	symbol,
	currencyFormatter.format(price)
)

fun List<CoinTicker>.toPresentation() = map { it.toPresentation() }