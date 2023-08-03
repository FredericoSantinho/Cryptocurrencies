package neuro.cryptocurrencies.presentation.mocks

import neuro.cryptocurrencies.presentation.model.CoinTickerModel

fun coinTickerModelMock() = CoinTickerModel("btc-bitcoin", "Bitcoin", 1, "BTC", "$ 25221.00")

fun coinTickerModelMockList() = listOf(coinTickerModelMock())