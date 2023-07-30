package neuro.coin.paprika.data.mapper

import neuro.coin.paprika.data.model.database.coin.RoomCoin
import neuro.coin.paprika.data.model.network.coin.CoinDto
import neuro.coin.paprika.domain.entity.Coin

fun RoomCoin.toDomain() = Coin(id, isActive, name, rank, symbol)

fun List<RoomCoin>.toDomain() = map { it.toDomain() }

fun CoinDto.toRoomCoin() = RoomCoin(id, isActive, name, rank, symbol)