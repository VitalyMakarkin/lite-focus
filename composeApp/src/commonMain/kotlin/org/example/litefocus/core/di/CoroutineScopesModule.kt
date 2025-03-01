package org.example.litefocus.core.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val coroutineScopesModule = module {
    single<CoroutineScope> { CoroutineScope(SupervisorJob()) }
}