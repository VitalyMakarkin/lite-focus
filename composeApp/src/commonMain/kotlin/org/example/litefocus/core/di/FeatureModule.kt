package org.example.litefocus.core.di

import org.example.litefocus.feature.countdowntimer.di.countdownTimerModule
import org.koin.dsl.module

val featureModule = module {
    includes(countdownTimerModule)
}