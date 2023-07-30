package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.entity.Team
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.TeamModel

fun CoinDetails.toPresentation() = CoinDetailsModel(
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
	tags,
	team.toPresentation(),
	type
)

fun Team.toPresentation() = TeamModel(name, position)

fun List<Team>.toPresentation() = map { it.toPresentation() }