package neuro.cryptocurrencies.data.mocks.network

import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import neuro.cryptocurrencies.data.model.network.coin.tickers.Quotes
import neuro.cryptocurrencies.data.model.network.coin.tickers.USD

fun coinTickerDtoMock() = CoinTickerDto("btc-bitcoin", "Bitcoin", Quotes(USD(25221.0)), 1, "BTC")

fun coinTickerDtoMockList() = listOf(
	coinTickerDtoMock(),
	CoinTickerDto("eth-ethereum", "Ethereum", Quotes(USD(1543.0)), 2, "ETC")
)