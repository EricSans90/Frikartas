package com.example.frikartas.data.sources.remote

import com.example.frikartas.data.models.ApiResponse
import com.example.frikartas.data.models.CollectionDTO
import retrofit2.Response
import retrofit2.http.GET

interface CardApiService {
    @GET("collections")
    suspend fun getAllCollections(): Response<ApiResponse<List<CollectionDTO>>>

    @GET("endpoint")
    suspend fun fetchCollections(): Response<List<CollectionDTO>>
}