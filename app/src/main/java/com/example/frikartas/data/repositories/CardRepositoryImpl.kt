package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.domain.repositories.CardRepository
import com.example.frikartas.data.mappers.mapCollectionDTOToCollection
import com.example.frikartas.data.sources.remote.CardRemoteDataSource
import com.example.frikartas.domain.models.Collection
import javax.inject.Inject

/**
 * Implementación concreta del repositorio que maneja la obtención de datos de cartas.
 *
 * @property localDataSource fuente de datos local para acceder a las colecciones de cartas.
 * @property remoteDataSource fuente de datos remota para obtener colecciones de cartas desde una API.
 */
class CardRepositoryImpl @Inject constructor(
    private val localDataSource: CardLocalDataSource,
    private val remoteDataSource: CardRemoteDataSource
) : CardRepository {

    /**
     * Obtiene las colecciones de cartas desde la fuente de datos remota o local.
     *
     * Intenta primero obtener las colecciones de la API remota y si falla,
     * recurre a la fuente de datos local.
     *
     * @return Una lista de colecciones de cartas.
     */
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
