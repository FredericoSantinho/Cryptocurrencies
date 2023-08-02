package neuro.cryptocurrencies.data.mocks

import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.details.Links
import neuro.cryptocurrencies.data.model.network.coin.details.Whitepaper

fun coinDetailsDtoMock() = CoinDetailsDto(
	"description",
	"",
	"",
	true,
	"SHA-256",
	"btc-bitcoin",
	true,
	true,
	"",
	Links(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
	emptyList(),
	"logo",
	"",
	"Bitcoin",
	true,
	"",
	"POW",
	1,
	"",
	"BTC",
	emptyList(),
	emptyList(),
	"coin",
	Whitepaper("", "")
)
