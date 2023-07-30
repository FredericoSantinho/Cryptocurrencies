package neuro.cryptocurrencies.data.mapper

import neuro.cryptocurrencies.data.model.network.coin.CoinDto
import neuro.cryptocurrencies.domain.entity.Coin

fun CoinDto.toDomain() = Coin(id, isActive, name, rank, symbol)