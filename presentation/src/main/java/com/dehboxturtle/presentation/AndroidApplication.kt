package com.dehboxturtle.presentation

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.facebook.stetho.Stetho
import com.dehboxturtle.data.di.components.DaggerDataComponent
import com.dehboxturtle.data.di.components.DataComponent
import com.dehboxturtle.presentation.di.components.ApplicationComponent
import com.dehboxturtle.presentation.di.components.DaggerApplicationComponent
import timber.log.Timber

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * Android Main Application
 */
class AndroidApplication : Application() {

    @set:VisibleForTesting
    lateinit var appComponent: ApplicationComponent

    @VisibleForTesting
    val dataComponent: DataComponent by lazy { DaggerDataComponent.factory().create(baseContext) }

    override fun onCreate() {
        super.onCreate()

        // Init Stetho
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        // Init Debug log
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Create App Component
        appComponent = createAppComponent()
    }

    @VisibleForTesting
    fun createAppComponent(): ApplicationComponent =
        DaggerApplicationComponent.factory()
            .create(this, dataComponent)

}
