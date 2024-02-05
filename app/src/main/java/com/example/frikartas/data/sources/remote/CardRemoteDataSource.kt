package com.example.frikartas.data.sources.remote

import com.example.frikartas.data.models.CollectionDTO
import retrofit2.Response
import javax.inject.Inject

/**
 * Fuente de datos remota que utiliza un servicio de API para obtener datos de cartas.
 *
 * @property apiService Instancia del servicio de API que será utilizado para hacer llamadas de red.
 */
class CardRemoteDataSource @Inject constructor(private val apiService: CardApiService) {

    /**
     * Obtiene las colecciones de cartas desde la fuente de datos remota.
     *
     * @return Una respuesta de Retrofit que contiene la lista de CollectionDTO.
     */
    suspend fun fetchCollections(): Response<List<CollectionDTO>> {
        return apiService.fetchCollections()
    }
}