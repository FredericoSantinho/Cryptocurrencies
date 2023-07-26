package neuro.coin.paprika.data.mapper

import neuro.coin.paprika.data.model.coin.CoinDto
import neuro.coin.paprika.domain.entity.Coin

fun CoinDto.toDomain() = Coin(id, is_active, name, rank, symbol, type)