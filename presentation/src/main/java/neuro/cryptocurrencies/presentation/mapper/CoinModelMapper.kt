package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.Coin
import neuro.cryptocurrencies.presentation.model.CoinModel

fun Coin.toPresentation() = CoinModel(id, isActive, name, rank, symbol)

fun List<Coin>.toPresentation() = map { it.toPresentation() }