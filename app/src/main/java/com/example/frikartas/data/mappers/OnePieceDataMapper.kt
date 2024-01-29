package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.OnePieceCardDTO
import com.example.frikartas.data.models.OnePieceCollectionDTO
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.models.OnePieceCollection

fun mapOnePieceCardDTOToCard(onePieceCardDTO: OnePieceCardDTO) = OnePieceCard(
    name = onePieceCardDTO.name,
    //de momento no lo necesito -> tags = onePieceCardDTO.tags,
    rarity = onePieceCardDTO.rarity,
    stock = onePieceCardDTO.stock,
    price = onePieceCardDTO.price,
    discount = onePieceCardDTO.discount,
    SKU = onePieceCardDTO.SKU,
    urlImages = onePieceCardDTO.urlImages
    //de momento no lo necesito -> description = onePieceCardDTO.description,
    //de momento no lo necesito -> languages = onePieceCardDTO.languages.map { it.displayName },
    //de momento no lo necesito -> size = onePieceCardDTO.size,
)

fun mapOnePieceCollectionDTOToCollection(onePieceCollectionDTO: OnePieceCollectionDTO) = OnePieceCollection(
    name = onePieceCollectionDTO.name,
    publicationYear = onePieceCollectionDTO.publicationYear,
    cards = onePieceCollectionDTO.cards.map { mapOnePieceCardDTOToCard(it) }
)