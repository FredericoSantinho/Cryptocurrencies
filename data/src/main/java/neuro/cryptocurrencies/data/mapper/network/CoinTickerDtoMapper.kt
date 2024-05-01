package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import neuro.cryptocurrencies.domain.entity.CoinTicker

fun CoinTickerDto.toDomain() =
	CoinTicker(id, name, rank, symbol, quotes?.usd?.price ?: 0.0)