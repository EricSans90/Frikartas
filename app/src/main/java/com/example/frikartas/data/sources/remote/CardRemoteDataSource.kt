package com.example.frikartas.data.sources.remote

import com.example.frikartas.data.models.CollectionDTO
import retrofit2.Response
import javax.inject.Inject

class CardRemoteDataSource @Inject constructor(private val apiService: CardApiService) {

    suspend fun fetchCollections(): Response<List<CollectionDTO>> {
        return apiService.fetchCollections()
    }
}