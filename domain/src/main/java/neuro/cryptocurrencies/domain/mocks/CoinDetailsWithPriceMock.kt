package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

fun coinDetailsWithPriceMock(addToPrice: Int = 0) =
	CoinDetailsWithPrice(coinDetailsMock(), 25221.0 + addToPrice)