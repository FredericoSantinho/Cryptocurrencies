package neuro.cryptocurrencies.data.mocks

import neuro.cryptocurrencies.domain.entity.CoinDetails

fun coinDetailsMock(): CoinDetails {
	return CoinDetails(
		"btc-bitcoin",
		"description",
		"SHA-256",
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
}
