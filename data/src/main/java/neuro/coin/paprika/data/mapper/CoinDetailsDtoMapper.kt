package neuro.coin.paprika.data.mapper

import neuro.coin.paprika.data.model.coin.details.CoinDetailsDto
import neuro.coin.paprika.data.model.coin.details.Team
import neuro.coin.paprika.domain.entity.CoinDetails

fun CoinDetailsDto.toDomain(): CoinDetails {
	return CoinDetails(
		description,
		hash_algorithm,
		id,
		is_active,
		logo,
		name,
		open_source,
		proof_type,
		rank,
		symbol,
		tags.map { it.name },
		team.map { it.toDomain() },
		type
	)
}

fun Team.toDomain() = neuro.coin.paprika.domain.entity.Team(id, name, position)