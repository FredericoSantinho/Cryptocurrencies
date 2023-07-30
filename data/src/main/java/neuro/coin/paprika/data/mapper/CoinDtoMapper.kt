package neuro.coin.paprika.data.mapper

import neuro.coin.paprika.data.model.network.coin.CoinDto
import neuro.coin.paprika.domain.entity.Coin

fun CoinDto.toDomain() = Coin(id, isActive, name, rank, symbol)