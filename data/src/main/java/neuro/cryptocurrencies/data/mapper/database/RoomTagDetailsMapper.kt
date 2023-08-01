package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.tag.RoomTagDetails
import neuro.cryptocurrencies.domain.entity.TagDetails

fun RoomTagDetails.toDomain() = TagDetails(id, name, description)