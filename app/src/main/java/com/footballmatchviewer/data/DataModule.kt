package com.footballmatchviewer.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    internal fun provideMatchesDataSource(impl: MatchesRepository): MatchesDataSource = impl
}