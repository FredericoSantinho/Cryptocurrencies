package neuro.cryptocurrencies.presentation.mapper

import neuro.cryptocurrencies.domain.entity.CoinDetails
import neuro.cryptocurrencies.domain.entity.Tag
import neuro.cryptocurrencies.domain.entity.TeamMember
import neuro.cryptocurrencies.presentation.model.CoinDetailsModel
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamMemberModel

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

fun TeamMember.toPresentation() = TeamMemberModel(id, name, position)

fun Tag.toPresentation() = TagModel(id, name)