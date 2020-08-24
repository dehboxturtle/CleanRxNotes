package com.dehboxturtle.presentation.di.components

import android.app.Application
import com.dehboxturtle.data.di.components.DataComponent
import com.dehboxturtle.presentation.di.PerApplication
import com.dehboxturtle.presentation.di.modules.ApplicationModule
import com.dehboxturtle.presentation.di.modules.UseCaseModule
import dagger.BindsInstance
import dagger.Component

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * A component whose lifetime is the life of the application.
 */
@PerApplication // Constraints this component to one-per-application or unscoped bindings.
@Component(
    dependencies = [(DataComponent::class)],
    modules = [(ApplicationModule::class), (UseCaseModule::class)]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            dataComponent: DataComponent
        ): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory

}
