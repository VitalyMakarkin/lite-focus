package org.example.litefocus.core.di

import org.example.litefocus.core.domain.ObserveCountdownTimerUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::ObserveCountdownTimerUseCase)
}