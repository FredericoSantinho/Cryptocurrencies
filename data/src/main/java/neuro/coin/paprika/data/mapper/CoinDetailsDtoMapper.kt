package neuro.coin.paprika.data.mapper

import neuro.coin.paprika.data.model.network.coin.details.CoinDetailsDto
import neuro.coin.paprika.data.model.network.coin.details.Team
import neuro.coin.paprika.domain.entity.CoinDetails

fun CoinDetailsDto.toDomain(): CoinDetails {
	return CoinDetails(
		description,
		hashAlgorithm,
		id,
		isActive,
		logo,
		name,
		openSource,
		proofType,
		rank,
		symbol,
		tags.map { it.name },
		team.map { it.toDomain() },
		type
	)
}

fun Team.toDomain() = neuro.coin.paprika.domain.entity.Team(id, name, position)