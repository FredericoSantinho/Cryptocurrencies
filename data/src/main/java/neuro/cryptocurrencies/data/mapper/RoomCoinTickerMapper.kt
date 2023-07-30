package neuro.cryptocurrencies.data.mapper

import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.domain.entity.CoinTicker

fun RoomCoinTicker.toDomain() = CoinTicker(id, name, rank, symbol, price, percentChange24h)

fun List<RoomCoinTicker>.toDomain() = map { it.toDomain() }