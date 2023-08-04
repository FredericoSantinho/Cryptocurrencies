package neuro.cryptocurrencies.presentation.mocks

import neuro.cryptocurrencies.presentation.model.TagModel

fun tagModelMock() = TagModel("segwit", "Segwit")

fun tagModelMockList() = listOf(tagModelMock())