package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.details.Team
import neuro.cryptocurrencies.domain.entity.CoinDetails

fun CoinDetailsDto.toDomain(): CoinDetails {
	return CoinDetails(
		description ?: "",
		hashAlgorithm = "",
		id,
		isActive,
		logo,
		name,
		openSource,
		proofType = "",
		rank,
		symbol,
		tags?.map { it.name } ?: emptyList(),
		team.map { it.toDomain() },
		type
	)
}

fun Team.toDomain() = neuro.cryptocurrencies.domain.entity.Team(id, name, position)