package neuro.coin.paprika.presentation.mapper

import neuro.coin.paprika.domain.entity.CoinDetails
import neuro.coin.paprika.domain.entity.Team
import neuro.coin.paprika.presentation.model.CoinDetailsModel
import neuro.coin.paprika.presentation.model.TeamModel

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