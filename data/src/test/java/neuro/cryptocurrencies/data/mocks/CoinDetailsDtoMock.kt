package neuro.cryptocurrencies.data.mocks

import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto

fun coinDetailsDtoMock() = CoinDetailsDto(
	"description",
	"SHA-256",
	"btc-bitcoin",
	"logo",
	"Bitcoin",
	true,
	"POW",
	1,
	"BTC",
	emptyList(),
	emptyList(),
	"coin"
)
