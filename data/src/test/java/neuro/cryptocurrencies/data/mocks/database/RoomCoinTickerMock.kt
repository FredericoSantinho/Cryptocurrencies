package neuro.cryptocurrencies.data.mocks.database

import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker

fun roomCoinTickerMock() = RoomCoinTicker("btc-bitcoin", "Bitcoin", 1, "BTC", 25221.0)

fun roomCoinTickerMockList() = listOf(
	roomCoinTickerMock(),
	RoomCoinTicker("eth-ethereum", "Ethereum", 2, "ETC", 1543.0)
)