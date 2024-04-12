package dev.da0hn.file.management.system.data.db.mappers

import dev.da0hn.file.management.system.core.domain.Entity
import dev.da0hn.file.management.system.core.domain.EntityId
import org.springframework.core.convert.converter.Converter

interface EntityToDomainConverter<IN, OUT : Entity<out EntityId>> : Converter<IN, OUT> {

  override fun convert(source: IN & Any): OUT? {
    return toDomain(source)
  }

  fun toDomain(source: IN): OUT


}
