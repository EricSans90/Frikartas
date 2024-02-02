package com.example.frikartas.di

import android.content.Context
import com.example.frikartas.data.repositories.CardRepositoryImpl
import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.domain.repositories.CardRepository
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
    abstract fun bindCardRepository(
        cardRepositoryImpl: CardRepositoryImpl
    ): CardRepository

    companion object {
        @Provides
        fun provideCardLocalDataSource(@ApplicationContext context: Context): CardLocalDataSource {
            return CardLocalDataSource(context)
        }

        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }
}