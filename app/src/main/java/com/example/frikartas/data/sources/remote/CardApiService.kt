package com.example.frikartas.data.sources.remote

import com.example.frikartas.data.models.ApiResponse
import com.example.frikartas.data.models.CollectionDTO
import retrofit2.Response
import retrofit2.http.GET

//La API la implementaré bien para el proyecto de final de grado

/**
 * Servicio de API para acceder a los endpoints relacionados con las colecciones de cartas.
 *
 * La implementación de estos métodos será proporcionada por Retrofit.
 */
interface CardApiService {
    /**
     * Obtiene todas las colecciones de cartas desde la API remota.
     *
     * @return Una respuesta de Retrofit que contiene la ApiResponse con la lista de CollectionDTO.
     */
    @GET("collections")
    suspend fun getAllCollections(): Response<ApiResponse<List<CollectionDTO>>>

    /**
     * Obtiene colecciones de cartas desde un endpoint específico de la API.
     *
     * @return Una respuesta de Retrofit que contiene la lista de CollectionDTO.
     */
    @GET("endpoint")
    suspend fun fetchCollections(): Response<List<CollectionDTO>>
}