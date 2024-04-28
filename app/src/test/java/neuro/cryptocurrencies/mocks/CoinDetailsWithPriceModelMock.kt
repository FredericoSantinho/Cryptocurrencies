package neuro.cryptocurrencies.mocks

import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel

fun coinDetailsWithPriceModelMock() =
	CoinDetailsWithPriceModel(coinDetailsModelMock(), "$ 25221.00")