package neuro.cryptocurrencies.presentation.mocks

import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel

fun coinDetailsWithPriceModelMock() =
	CoinDetailsWithPriceModel(coinDetailsModelMock(), "$ 25221.00")