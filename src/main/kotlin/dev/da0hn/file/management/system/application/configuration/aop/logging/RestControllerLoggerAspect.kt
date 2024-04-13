package dev.da0hn.file.management.system.application.configuration.aop.logging

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.CodeSignature
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.lang.reflect.Method
import kotlin.reflect.full.hasAnnotation


@Aspect
@Component
class RestControllerLoggerAspect(
  private val objectMapper: ObjectMapper,
) {

  val logger = KotlinLogging.logger { }


  @Before(
    "execution(* dev.da0hn.file.management.system.application.controller.*.*(..)) && @within(org.springframework.web.bind.annotation.RestController)"
  )
  fun logMethod(joinPoint: JoinPoint) {
    val signature = joinPoint.signature as MethodSignature

    val methodName = signature.method.name

    val rootPath = signature.method.declaringClass.annotations
      .first { RequestMapping::class.java.isInstance(it) }
      .let { it as RequestMapping }
      .value[0]

    val metadata = signature.method.annotations
      .filter { it.annotationClass.hasAnnotation<RequestMapping>() }
      .flatMap { it.annotationClass.annotations }
      .find { RequestMapping::class.java.isInstance(it) }
      .let { it as RequestMapping }

    val innerPath = getHttpAnnotationFieldValue(signature.method, "value")

    val parameters = getParameters(joinPoint)

    try {
      val parametersAsJson = this.objectMapper.writeValueAsString(parameters)
      logger.info { "path=${metadata.method[0]} ${rootPath}${innerPath} | m=${methodName}($parametersAsJson)" }
    } catch (e: JsonProcessingException) {
      logger.error("Error while trying converting request parameters", e)
    }
  }

  private fun getParameters(joinPoint: JoinPoint): Map<String, Any> {
    val signature = joinPoint.signature as CodeSignature

    val map = HashMap<String, Any>()

    val parameterNames = signature.parameterNames

    for (i in parameterNames.indices) {
      map[parameterNames[i]] = joinPoint.args[i]
    }

    return map
  }

  fun getHttpAnnotationFieldValue(method: Method, field: String): String {
    val httpVerbAnnotations = listOf(
      GetMapping::class.java,
      PostMapping::class.java,
      PutMapping::class.java,
      DeleteMapping::class.java,
      PatchMapping::class.java
    )

    val firstNotNullOf = httpVerbAnnotations.firstNotNullOf { annotationType ->
      method.getAnnotation(annotationType)?.let { annotation ->
        val valueMethod = annotationType.getDeclaredMethod(field)
        val values = valueMethod.invoke(annotation) as Array<*>
        values.map { it.toString() }.firstOrNull().let { "" }
      }
    }
    return firstNotNullOf
  }

}
