package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.CollectionDTO
import com.example.frikartas.domain.models.Collection

/**
 * Convierte un CollectionDTO a un objeto de dominio Collection.
 *
 * Esta función toma un CollectionDTO que proviene de una fuente de datos externa,
 * como puede ser una respuesta de API, y lo convierte en un objeto Collection del dominio
 * que se puede utilizar en la lógica de la aplicación.
 *
 * @param collectionDTO Objeto CollectionDTO que contiene la información de la colección.
 * @return Un objeto Collection con datos mapeados del DTO.
 */
fun mapCollectionDTOToCollection(collectionDTO: CollectionDTO): Collection {
    val collection = Collection(
        collectionId = collectionDTO.collectionId,
        collectType = collectionDTO.collectType,
        name = collectionDTO.name,
        publicationYear = collectionDTO.publicationYear,
        cards = mutableListOf() // Inicializa con una lista vacía, se llena luego
    )

    // Mapea cada CardDTO a un objeto Card y los asigna a la colección
    collection.cards = collectionDTO.cards.map {
        mapCardDTOToCard(it, collection)
    }

    return collection
}