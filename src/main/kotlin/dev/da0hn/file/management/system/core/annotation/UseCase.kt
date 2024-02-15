package dev.da0hn.file.management.system.core.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component
import org.springframework.stereotype.Indexed

@Indexed
@Component
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class UseCase(
  @get:AliasFor(annotation = Component::class)
  val value: String = "",
)
