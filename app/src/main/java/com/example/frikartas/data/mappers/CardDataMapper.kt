package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.CardDTO
import com.example.frikartas.data.models.CollectionDTO
import com.example.frikartas.domain.models.Card
import com.example.frikartas.domain.models.Collection

fun mapCardDTOToCard(cardDTO: CardDTO, collection: Collection) = Card(
    name = cardDTO.name,
    tags = cardDTO.tags,
    rarity = cardDTO.rarity,
    stock = cardDTO.stock,
    price = cardDTO.price,
    discount = cardDTO.discount,
    SKU = cardDTO.SKU,
    urlImages = cardDTO.urlImages,
    description = cardDTO.description,
    languages = cardDTO.languages,
    size = cardDTO.size,
    collection = collection
)

fun mapCollectionDTOToCollection(collectionDTO: CollectionDTO): Collection {
    val collection = Collection(
        name = collectionDTO.name,
        publicationYear = collectionDTO.publicationYear,
        cards = mutableListOf() // Inicializa con una lista vacía, se llena luego
    )

    collection.cards = collectionDTO.cards.map {
        mapCardDTOToCard(it, collection)
    }

    return collection
}