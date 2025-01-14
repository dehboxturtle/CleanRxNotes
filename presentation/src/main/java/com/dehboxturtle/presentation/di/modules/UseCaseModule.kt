package com.dehboxturtle.presentation.di.modules

import com.dehboxturtle.data.helper.TimberWrapper
import com.dehboxturtle.domain.usecases.base.Logger
import com.dehboxturtle.domain.usecases.base.UseCaseScheduler
import com.dehboxturtle.presentation.di.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * Dagger module that provides use cases from domain.
 */
@Module
class UseCaseModule {

    @Provides
    @PerApplication
    internal fun providePostScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @PerApplication
    internal fun provideUseCaseScheduler(postScheduler: Scheduler) =
        UseCaseScheduler(Schedulers.io(), postScheduler)

    @Provides
    @PerApplication
    internal fun provideLogger(): Logger = object : Logger {
        override fun log(message: () -> String) {
            TimberWrapper.d(message)
        }

        override fun logError(throwable: () -> Throwable) {
            TimberWrapper.e(throwable)
        }
    }

}
