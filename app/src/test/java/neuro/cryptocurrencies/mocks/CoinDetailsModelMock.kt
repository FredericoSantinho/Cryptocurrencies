package neuro.cryptocurrencies.mocks

import neuro.cryptocurrencies.presentation.model.CoinDetailsModel

fun coinDetailsModelMock() = CoinDetailsModel(
	"btc-bitcoin",
	"description",
	"SHA-256",
	"logo",
	"Bitcoin",
	true,
	"POW",
	1,
	"BTC",
	tagModelMockList(),
	teamMemberModelMockList(),
	"coin"
)
