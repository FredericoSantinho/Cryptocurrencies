package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.tag.TagDto
import neuro.cryptocurrencies.domain.entity.TagDetails

fun TagDto.toDomain() = TagDetails(id, name, description)
