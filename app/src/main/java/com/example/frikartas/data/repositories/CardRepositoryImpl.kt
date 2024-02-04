package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.domain.repositories.CardRepository
import com.example.frikartas.data.mappers.mapCollectionDTOToCollection
import com.example.frikartas.data.sources.remote.CardRemoteDataSource
import com.example.frikartas.domain.models.Collection
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val localDataSource: CardLocalDataSource,
    private val remoteDataSource: CardRemoteDataSource
) : CardRepository {

    override suspend fun getCardCollections(): List<Collection> {
        return try {
            // Intenta obtener datos de la API
            val collectionDTOsFromApi = remoteDataSource.fetchCollections().body()
            collectionDTOsFromApi?.map { mapCollectionDTOToCollection(it) } ?: emptyList()
        } catch (e: Exception) {
            // Si algo falla, recurre a los datos locales
            val collectionDTOs = localDataSource.getCollections()
            collectionDTOs.map { mapCollectionDTOToCollection(it) }
        }
    }
}
