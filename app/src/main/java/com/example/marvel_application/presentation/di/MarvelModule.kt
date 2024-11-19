package com.example.marvel_application.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.marvel_application.presentation.data.database.CharacterDao
import com.example.marvel_application.presentation.data.database.MarvelDataBase
import com.example.marvel_application.presentation.data.repository.CharacterMapper
import com.example.marvel_application.presentation.data.repository.MarvelRepository
import com.example.marvel_application.presentation.network.MarvelApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MarvelDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            MarvelDataBase::class.java,
            "marvel_database"
        ).build()
    }

    @Provides
    fun provideCharacterDao(database: MarvelDataBase): CharacterDao {
        return database.characterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    @Singleton
    fun provideMarvelRepository(
        apiService: MarvelApiService,
        characterDao: CharacterDao,
        characterMapper: CharacterMapper
    ): MarvelRepository {
        return MarvelRepository(apiService, characterDao, characterMapper)
    }
}