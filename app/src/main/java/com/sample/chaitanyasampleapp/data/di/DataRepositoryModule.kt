package com.sample.chaitanyasampleapp.data.di

import com.sample.chaitanyasampleapp.data.repository.DataRepositoryImpl
import com.sample.chaitanyasampleapp.domain.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}