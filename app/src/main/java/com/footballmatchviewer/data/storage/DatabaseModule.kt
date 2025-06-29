package com.footballmatchviewer.data.storage

import android.content.Context
import androidx.room.Room
import com.footballmatchviewer.data.MatchesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "match_database"
        ).build()
    }

    @Provides
    fun provideMatchDao(db: AppDatabase): MatchDao = db.matchDao()

    @Provides
    fun provideTeamDao(db: AppDatabase): TeamDao = db.teamDao()

    @Provides
    @Database
    internal fun provideDatabaseDataSource(impl: StorageMatchesDataSource): MatchesDataSource = impl

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Database
}
