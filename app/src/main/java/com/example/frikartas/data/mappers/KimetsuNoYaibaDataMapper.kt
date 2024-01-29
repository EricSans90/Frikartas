package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.KimetsuNoYaibaCardDTO
import com.example.frikartas.data.models.KimetsuNoYaibaCollectionDTO
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.models.KimetsuNoYaibaCollection

fun mapKimetsuNoYaibaCardDTOToCard(kimetsuNoYaibaCardDTO: KimetsuNoYaibaCardDTO) = KimetsuNoYaibaCard(
    name = kimetsuNoYaibaCardDTO.name,
    //de momento no lo necesito -> tags = onePieceCardDTO.tags,
    rarity = kimetsuNoYaibaCardDTO.rarity,
    stock = kimetsuNoYaibaCardDTO.stock,
    price = kimetsuNoYaibaCardDTO.price,
    discount = kimetsuNoYaibaCardDTO.discount,
    SKU = kimetsuNoYaibaCardDTO.SKU,
    urlImages = kimetsuNoYaibaCardDTO.urlImages
    //de momento no lo necesito -> description = onePieceCardDTO.description,
    //de momento no lo necesito -> languages = onePieceCardDTO.languages.map { it.displayName },
    //de momento no lo necesito -> size = onePieceCardDTO.size,
)

fun mapKimetsuNoYaibaCollectionDTOToCollection(kimetsuNoYaibaCollectionDTO: KimetsuNoYaibaCollectionDTO) = KimetsuNoYaibaCollection(
    name = kimetsuNoYaibaCollectionDTO.name,
    publicationYear = kimetsuNoYaibaCollectionDTO.publicationYear,
    cards = kimetsuNoYaibaCollectionDTO.cards.map { mapKimetsuNoYaibaCardDTOToCard(it) }
)