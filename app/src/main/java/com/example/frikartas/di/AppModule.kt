package com.example.frikartas.di

import android.content.Context
import com.example.frikartas.data.repositories.KimetsuNoYaibaRepositoryImpl
import com.example.frikartas.data.repositories.OnePieceRepositoryImpl
import com.example.frikartas.data.sources.local.KimetsuNoYaibaLocalDataSource
import com.example.frikartas.data.sources.local.OnePieceLocalDataSource
import com.example.frikartas.domain.repositories.KimetsuNoYaibaRepository
import com.example.frikartas.domain.repositories.OnePieceRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindOnePieceRepository(
        onePieceRepositoryImpl: OnePieceRepositoryImpl
    ): OnePieceRepository

    @Binds
    abstract fun bindKimetsuNoYaibaRepository(
        kimetsuNoYaibaRepositoryImpl: KimetsuNoYaibaRepositoryImpl
    ): KimetsuNoYaibaRepository

    companion object {
        @Provides
        fun provideOnePieceLocalDataSource(@ApplicationContext context: Context): OnePieceLocalDataSource {
            return OnePieceLocalDataSource(context)
        }

        @Provides
        fun provideKimetsuNoYaibaLocalDataSource(@ApplicationContext context: Context): KimetsuNoYaibaLocalDataSource {
            return KimetsuNoYaibaLocalDataSource(context)
        }

        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }

}