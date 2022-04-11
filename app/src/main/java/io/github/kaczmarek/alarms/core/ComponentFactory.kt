package io.github.kaczmarek.alarms.core

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * Используется для создания Decompose-компонентов.
 * Сами методы для создания компонентов реализуются как extension-функции в соответствующих feature-модулях.
 */
class ComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}