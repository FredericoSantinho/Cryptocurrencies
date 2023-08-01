package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPrice
import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.details.Tag
import neuro.cryptocurrencies.data.model.network.coin.details.Team
import neuro.cryptocurrencies.domain.entity.CoinDetails

fun CoinDetailsDto.toDatabase(price: Double) = RoomCoinDetailsWithPrice(
	id,
	description ?: "",
	hashAlgorithm ?: "",
	logo,
	name,
	openSource,
	proofType ?: "",
	rank,
	symbol,
	type,
	price
)

fun CoinDetailsDto.toDomain(): CoinDetails {
	return CoinDetails(
		id,
		description ?: "",
		hashAlgorithm ?: "",
		logo,
		name,
		openSource,
		proofType ?: "",
		rank,
		symbol,
		tags?.map { it.toDomain() } ?: emptyList(),
		team.map { it.toDomain() },
		type
	)
}

fun Team.toDomain() = neuro.cryptocurrencies.domain.entity.Team(id, name, position)

fun Tag.toDomain() = neuro.cryptocurrencies.domain.entity.Tag(id, name)