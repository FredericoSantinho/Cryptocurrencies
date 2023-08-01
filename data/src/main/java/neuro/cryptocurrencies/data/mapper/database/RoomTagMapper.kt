package neuro.cryptocurrencies.data.mapper.database

import neuro.cryptocurrencies.data.model.database.tag.RoomTag
import neuro.cryptocurrencies.domain.entity.Tag

fun Tag.toDatabase(coinDetailsId: String) = RoomTag(id, name, coinDetailsId)

fun RoomTag.toDomain() = Tag(id, name)