package neuro.cryptocurrencies.data.mapper.network

import neuro.cryptocurrencies.data.model.network.tag.TagDetailsDto
import neuro.cryptocurrencies.domain.entity.TagDetails

fun TagDetailsDto.toDomain() = TagDetails(id, name, description)
