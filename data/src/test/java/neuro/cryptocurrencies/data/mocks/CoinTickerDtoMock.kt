package neuro.cryptocurrencies.data.mocks

import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import neuro.cryptocurrencies.data.model.network.coin.tickers.Quotes
import neuro.cryptocurrencies.data.model.network.coin.tickers.USD

fun coinTickerDtoMock() = CoinTickerDto("btc-bitcoin", "Bitcoin", Quotes(USD(25221.0)), 1, "BTC")