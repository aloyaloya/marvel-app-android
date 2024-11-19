package com.example.marvel_application.presentation.di

import com.example.marvel_application.presentation.data.repository.MarvelRepository
import com.example.marvel_application.presentation.network.MarvelApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelModule {

    @Provides
    @Singleton
    fun provideMarvelRepository(apiService: MarvelApiService): MarvelRepository {
        return MarvelRepository(apiService)
    }
}