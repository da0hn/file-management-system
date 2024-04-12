package dev.da0hn.file.management.system.data.db.mappers

import dev.da0hn.file.management.system.core.domain.Entity
import dev.da0hn.file.management.system.core.domain.EntityId
import org.springframework.core.convert.converter.Converter

interface DomainToEntityConverter<IN : Entity<out EntityId>, OUT> : Converter<IN, OUT> {

  override fun convert(source: IN): OUT? {
    return toEntity(source)
  }

  fun toEntity(source: IN): OUT


}
