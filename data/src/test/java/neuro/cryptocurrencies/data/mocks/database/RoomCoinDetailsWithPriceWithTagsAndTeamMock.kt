package neuro.cryptocurrencies.data.mocks.database

import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPriceWithTagsAndTeam

fun roomCoinDetailsWithPriceWithTagsAndTeamMock() = RoomCoinDetailsWithPriceWithTagsAndTeam(
	roomCoinDetailsWithPriceMock(), emptyList(), emptyList()
)