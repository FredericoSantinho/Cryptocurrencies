package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl

private val currencyFormatter = CurrencyFormatterImpl(DecimalFormatterImpl(2), "$")

fun CoinDetailsWithPrice.toPresentation() =
	CoinDetailsWithPriceModel(coinDetails.toPresentation(), currencyFormatter.format(price))