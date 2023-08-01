package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPrice
import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPriceWithTagsAndTeam
import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice

fun CoinDetails.toDatabase(price: Double) = RoomCoinDetailsWithPrice(
	id,
	description,
	hashAlgorithm,
	logo,
	name,
	openSource,
	proofType,
	rank,
	symbol,
	type,
	price
)

fun RoomCoinDetailsWithPriceWithTagsAndTeam.toDomain() =
	CoinDetailsWithPrice(
		CoinDetails(
			roomCoinDetailsWithPrice.id,
			roomCoinDetailsWithPrice.description,
			roomCoinDetailsWithPrice.hashAlgorithm,
			roomCoinDetailsWithPrice.logo,
			roomCoinDetailsWithPrice.name,
			roomCoinDetailsWithPrice.openSource,
			roomCoinDetailsWithPrice.proofType,
			roomCoinDetailsWithPrice.rank,
			roomCoinDetailsWithPrice.symbol,
			tags.map { it.toDomain() },
			team.map { it.toDomain() },
			roomCoinDetailsWithPrice.type
		), roomCoinDetailsWithPrice.price
	)
