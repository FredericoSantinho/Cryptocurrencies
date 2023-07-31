package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.coin.RoomCoinTicker
import neuro.cryptocurrencies.domain.entity.CoinTicker

fun RoomCoinTicker.toDomain() = CoinTicker(id, name, rank, symbol, price)

fun List<RoomCoinTicker>.toDomain() = map { it.toDomain() }