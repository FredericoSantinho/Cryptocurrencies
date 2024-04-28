package neuro.cryptocurrencies.mocks

import neuro.cryptocurrencies.presentation.model.TagModel

fun tagModelMock() = TagModel("segwit", "Segwit")

fun tagModelMockList() = listOf(tagModelMock())