package org.example.litefocus.feature.countdowntimer.di

import org.example.litefocus.feature.countdowntimer.domain.CountdownTimerInteractor
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val countdownTimerModule = module {
    factoryOf(::CountdownTimerInteractor)
    viewModelOf(::CountdownTimerViewModel)
}