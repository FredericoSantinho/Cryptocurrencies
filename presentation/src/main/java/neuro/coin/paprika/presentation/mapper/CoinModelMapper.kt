package neuro.coin.paprika.presentation.mapper

import neuro.coin.paprika.domain.entity.Coin
import neuro.coin.paprika.presentation.model.CoinModel

fun Coin.toPresentation() = CoinModel(id, isActive, name, rank, symbol, type)