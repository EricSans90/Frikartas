package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.CollectionDTO
import com.example.frikartas.domain.models.Collection

fun mapCollectionDTOToCollection(collectionDTO: CollectionDTO): Collection {
    val collection = Collection(
        collectionId = collectionDTO.collectionId,
        collectType = collectionDTO.collectType,
        name = collectionDTO.name,
        publicationYear = collectionDTO.publicationYear,
        cards = mutableListOf() // Inicializa con una lista vacía, se llena luego
    )

    collection.cards = collectionDTO.cards.map {
        mapCardDTOToCard(it, collection)
    }

    return collection
}