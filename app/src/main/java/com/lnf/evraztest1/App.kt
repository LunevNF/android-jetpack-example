package com.lnf.evraztest1

import android.app.Application
import com.lnf.evraztest1.di.dbModule
import com.lnf.evraztest1.di.repoModule
import com.lnf.evraztest1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(dbModule, repoModule, viewModelModule)
}