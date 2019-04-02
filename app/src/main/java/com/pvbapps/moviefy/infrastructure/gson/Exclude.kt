package com.pvbapps.moviefy.infrastructure.gson

import kotlin.annotation.Retention
import kotlin.annotation.Target

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Exclude
