package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.CoinDetails

fun coinDetailsMock() = CoinDetails(
	"btc-bitcoin",
	"description",
	"SHA-256",
	"logo",
	"Bitcoin",
	true,
	"POW",
	1,
	"BTC",
	tagMockList(),
	teamMemberMockList(),
	"coin"
)
