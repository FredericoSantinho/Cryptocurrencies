package neuro.cryptocurrencies.data.mapper

import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.data.model.network.coin.tickers.CoinTickerDto

fun CoinTickerDto.toDatabase() =
	RoomCoinTicker(id, name, rank, symbol, quotes.usd.price, quotes.usd.percentChange24h)