package com.dehboxturtle.data.di.modules

import com.dehboxturtle.data.di.providers.NetworkChecker
import com.dehboxturtle.data.extensions.api
import com.dehboxturtle.data.mapper.NoteMapper
import com.dehboxturtle.data.mapper.RepoMapper
import com.dehboxturtle.data.persistence.processor.NoteProcessor
import com.dehboxturtle.data.persistence.processor.RepoProcessor
import com.dehboxturtle.data.repository.NoteDataRepository
import com.dehboxturtle.data.repository.RepoDataRepository
import com.dehboxturtle.domain.repository.NoteRepository
import com.dehboxturtle.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * Dagger module that provides Repository class.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideRepoDataRepository(
        retrofit: Retrofit,
        repoMapper: RepoMapper,
        repoProcessor: RepoProcessor,
        networkChecker: NetworkChecker
    ): RepoRepository =
        RepoDataRepository(retrofit.api(), repoMapper, repoProcessor, networkChecker)


    @Provides
    @Singleton
    internal fun provideNoteDataRepository(
        noteMapper: NoteMapper,
        noteProcessor: NoteProcessor,
        networkChecker: NetworkChecker
    ): NoteRepository =
        NoteDataRepository(noteMapper, noteProcessor, networkChecker)
}
