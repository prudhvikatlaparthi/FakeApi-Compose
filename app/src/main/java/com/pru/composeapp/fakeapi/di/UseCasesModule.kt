package com.pru.composeapp.fakeapi.di

import com.pru.composeapp.fakeapi.domain.Repository
import com.pru.composeapp.fakeapi.domain.usecases.PostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun providePostsUseCase(repository: Repository) = PostsUseCase(repository = repository)
}