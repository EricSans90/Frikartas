package com.example.frikartas.data.repositories

import com.example.frikartas.data.sources.local.CardLocalDataSource
import com.example.frikartas.domain.repositories.CardRepository
import com.example.frikartas.data.mappers.mapCollectionDTOToCollection
import com.example.frikartas.data.models.CollectionDTO
import com.example.frikartas.data.sources.remote.CardRemoteDataSource
import com.example.frikartas.domain.models.Collection
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val localDataSource: CardLocalDataSource,
    private val remoteDataSource: CardRemoteDataSource
) : CardRepository {

    override suspend fun getCardCollections(): List<Collection> {
        // Simulando que la llamada a la API fue exitosa, pero realmente estoy usando datos locales
        val collectionDTOsFromApi: List<CollectionDTO>? = null // Simulando la respuesta de la API

        // Si la llamada a la API fue exitosa (por ahora simulada), usar esos datos, si no, recurrir a los datos locales
        val collectionDTOs = collectionDTOsFromApi ?: localDataSource.getCollections()

        // Convertir DTOs a entidades de dominio
        return collectionDTOs.map { mapCollectionDTOToCollection(it) }
    }
}
