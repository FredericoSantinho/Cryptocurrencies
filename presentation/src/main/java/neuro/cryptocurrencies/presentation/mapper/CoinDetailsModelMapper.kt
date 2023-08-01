package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.entity.Tag
import neuro.cryptocurrencies.domain.entity.Team
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamModel

fun CoinDetails.toPresentation() = CoinDetailsModel(
	id,
	description,
	hashAlgorithm,
	logo,
	name,
	openSource,
	proofType,
	rank,
	symbol,
	tags.map { it.toPresentation() },
	team.map { it.toPresentation() },
	type
)

fun Team.toPresentation() = TeamModel(name, position)

fun Tag.toPresentation() = TagModel(id, name)