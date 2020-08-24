package com.dehboxturtle.data.di.components

import android.content.Context
import com.dehboxturtle.data.di.modules.NetModule
import com.dehboxturtle.data.di.modules.PersistenceModule
import com.dehboxturtle.data.di.modules.RepositoryModule
import com.dehboxturtle.domain.repository.NoteRepository
import com.dehboxturtle.domain.repository.RepoRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 */
@Singleton
@Component(modules = [(NetModule::class), (PersistenceModule::class), (RepositoryModule::class)])
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

    // Exposed to sub-graphs
    fun provideRepoRepository(): RepoRepository

    // Exposed to sub-graphs
    fun provideNoteRepository(): NoteRepository

}