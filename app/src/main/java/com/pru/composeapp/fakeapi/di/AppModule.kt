package com.pru.composeapp.fakeapi.di

import com.pru.composeapp.fakeapi.data.RepositorySDK
import com.pru.composeapp.fakeapi.data.remote.APIService
import com.pru.composeapp.fakeapi.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService() = APIService()

    @Provides
    @Singleton
    fun provideRepository(apiService: APIService): Repository =
        RepositorySDK(apiService = apiService)
}