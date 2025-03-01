package org.example.litefocus.core.di

import org.example.litefocus.core.data.CountdownTimerRepository
import org.example.litefocus.core.data.DefaultCountdownTimerRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::DefaultCountdownTimerRepository) bind CountdownTimerRepository::class
}