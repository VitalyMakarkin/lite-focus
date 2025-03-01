package org.example.litefocus.core.di

import org.example.litefocus.core.datasource.CountdownTimerDataSource
import org.example.litefocus.core.datasource.DefaultCountdownTimerDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::DefaultCountdownTimerDataSource) bind CountdownTimerDataSource::class
}