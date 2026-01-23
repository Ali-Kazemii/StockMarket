package com.example.stockmarket.di

import com.example.stockmarket.data.repository.*
import com.example.stockmarket.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        impl: StockRepositoryImpl
    ): StockRepository

    @Binds
    @Singleton
    abstract fun bindPortfolioRepository(
        impl: PortfolioRepositoryImpl
    ): PortfolioRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
