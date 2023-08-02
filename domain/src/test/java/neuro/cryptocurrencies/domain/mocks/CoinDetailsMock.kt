package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.CoinDetails

fun coinDetailsMock() = CoinDetails(
	"1",
	"description",
	"SHA256",
	"",
	"Bitcoin",
	true,
	"POW",
	1,
	"BTC",
	emptyList(),
	emptyList(),
	"coin"
)
