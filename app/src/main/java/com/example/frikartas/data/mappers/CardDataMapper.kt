package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.OnePieceCardDTO
import com.example.frikartas.data.models.OnePieceCollectionDTO
import com.example.frikartas.domain.models.Card
import com.example.frikartas.domain.models.Collection

fun mapOnePieceCardDTOToCard(onePieceCardDTO: OnePieceCardDTO, collection: Collection) = Card(
    name = onePieceCardDTO.name,
    tags = onePieceCardDTO.tags,
    rarity = onePieceCardDTO.rarity,
    stock = onePieceCardDTO.stock,
    price = onePieceCardDTO.price,
    discount = onePieceCardDTO.discount,
    SKU = onePieceCardDTO.SKU,
    urlImages = onePieceCardDTO.urlImages,
    description = onePieceCardDTO.description,
    languages = onePieceCardDTO.languages,
    size = onePieceCardDTO.size,
    collection = collection
)

fun mapOnePieceCollectionDTOToCollection(onePieceCollectionDTO: OnePieceCollectionDTO): Collection {
    val collection = Collection(
        name = onePieceCollectionDTO.name,
        publicationYear = onePieceCollectionDTO.publicationYear,
        cards = mutableListOf() // Inicializa con una lista vacía, se llena luego
    )

    collection.cards = onePieceCollectionDTO.cards.map {
        mapOnePieceCardDTOToCard(it, collection)
    }

    return collection
}