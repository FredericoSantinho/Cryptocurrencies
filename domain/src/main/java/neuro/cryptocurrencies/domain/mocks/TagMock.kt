package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.Tag

fun tagMock() = Tag("segwit", "Segwit")

fun tagMockList() = listOf(tagMock())