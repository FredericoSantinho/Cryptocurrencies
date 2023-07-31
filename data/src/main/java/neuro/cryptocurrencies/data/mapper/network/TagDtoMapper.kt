package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.database.tag.TagDto
import neuro.cryptocurrencies.domain.entity.TagDetails

fun TagDto.toDomain() = TagDetails(description, id, name)