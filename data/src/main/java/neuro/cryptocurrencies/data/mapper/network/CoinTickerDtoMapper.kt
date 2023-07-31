package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto
import neuro.cryptocurrencies.domain.entity.CoinTicker

fun CoinTickerDto.toDatabase() =
	RoomCoinTicker(id, name, rank, symbol, quotes.usd.price, quotes.usd.percentChange24h)

fun CoinTickerDto.toDomain() =
	CoinTicker(id, name, rank, symbol, quotes.usd.price, quotes.usd.percentChange24h)