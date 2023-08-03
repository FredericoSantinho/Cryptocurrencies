package neuro.cryptocurrencies.data.mocks.database

import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPrice

fun roomCoinDetailsWithPriceMock() = RoomCoinDetailsWithPrice(
	"btc-bitcoin",
	"description",
	"SHA-256",
	"logo",
	"Bitcoin",
	true,
	"POW",
	1,
	"BTC",
	"coin",
	25221.0
)