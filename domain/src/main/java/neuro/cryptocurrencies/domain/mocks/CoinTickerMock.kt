package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.CoinTicker

fun coinTickerMock() = CoinTicker("btc-bitcoin", "Bitcoin", 1, "BTC", 25221.0)

fun coinTickerMockList() = listOf(
	coinTickerMock(),
	CoinTicker("eth-ethereum", "Ethereum", 2, "ETC", 1543.0)
)