package neuro.cryptocurrencies.data.mapper

import neuro.cryptocurrencies.data.model.database.coin.RoomCoin
import neuro.cryptocurrencies.data.model.network.coin.CoinDto
import neuro.cryptocurrencies.domain.entity.Coin

fun RoomCoin.toDomain() = Coin(id, isActive, name, rank, symbol)

fun List<RoomCoin>.toDomain() = map { it.toDomain() }

fun CoinDto.toRoomCoin() = RoomCoin(id, isActive, name, rank, symbol)