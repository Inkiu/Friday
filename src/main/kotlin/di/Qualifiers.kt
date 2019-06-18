package di

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerLogin

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ImageFolder