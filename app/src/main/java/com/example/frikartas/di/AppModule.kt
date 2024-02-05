package com.example.frikartas.di

import android.content.Context
import com.example.frikartas.data.repositories.CardRepositoryImpl
import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.data.sources.remote.CardApiService
import com.example.frikartas.domain.repositories.CardRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * AppModule es un módulo de Dagger que proporciona dependencias a nivel de aplicación.
 * Este módulo utiliza Hilt para la inyección de dependencias y proporciona instancias singleton
 * de diferentes clases necesarias en toda la aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * Proporciona la implementación concreta de CardRepository.
     *
     * @param cardRepositoryImpl La implementación de CardRepository.
     * @return La instancia de CardRepository que se usará para la inyección de dependencias.
     */
    @Binds
    abstract fun bindCardRepository(
        cardRepositoryImpl: CardRepositoryImpl
    ): CardRepository

    companion object {
        /**
         * Proporciona una instancia de CardLocalDataSource.
         *
         * @param context El contexto de la aplicación, proporcionado por Hilt.
         * @return Una nueva instancia de CardLocalDataSource.
         */
        @Provides
        fun provideCardLocalDataSource(@ApplicationContext context: Context): CardLocalDataSource {
            return CardLocalDataSource(context)
        }

        /**
         * Proporciona una instancia de Gson para la serialización y deserialización de JSON.
         *
         * @return Una nueva instancia de Gson.
         */
        @Provides
        fun provideGson(): Gson {
            return Gson()
        }

        /**
         * Proporciona una instancia de Retrofit para realizar llamadas HTTP.
         *
         * @return Una nueva instancia de Retrofit configurada con la URL base y el convertidor de Gson.
         */
        @Provides
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            // Cambiar por la URL base que no implementaré en este proyecto pero SI en el de final de Grado
            //que será una ampliación de este y haré un servidor con springBoot
            .baseUrl("http://api_base_url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /**
         * Proporciona una instancia de CardApiService para acceder a los endpoints de la API de cartas.
         *
         * @param retrofit La instancia de Retrofit configurada.
         * @return Una instancia de CardApiService creada por Retrofit.
         */
        @Provides
        fun provideCardApiService(retrofit: Retrofit): CardApiService =
            retrofit.create(CardApiService::class.java)
    }
}