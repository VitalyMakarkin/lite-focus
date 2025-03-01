package org.example.litefocus.core.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        dispatcherModule,
        coroutineScopesModule,
        dataSourceModule,
        dataModule,
        domainModule,
        featureModule,
    )
}
