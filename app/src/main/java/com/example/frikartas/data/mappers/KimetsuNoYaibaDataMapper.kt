package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.KimetsuNoYaibaCardDTO
import com.example.frikartas.data.models.KimetsuNoYaibaCollectionDTO
import com.example.frikartas.domain.models.KimetsuNoYaibaCard
import com.example.frikartas.domain.models.KimetsuNoYaibaCollection

fun mapKimetsuNoYaibaCardDTOToCard(kimetsuNoYaibaCardDTO: KimetsuNoYaibaCardDTO) = KimetsuNoYaibaCard(
    name = kimetsuNoYaibaCardDTO.name,
    urlImg = kimetsuNoYaibaCardDTO.urlImg,
    rarity = kimetsuNoYaibaCardDTO.rarity,
    stock = kimetsuNoYaibaCardDTO.stock,
    precio = kimetsuNoYaibaCardDTO.precio,
    discount = kimetsuNoYaibaCardDTO.discount,
)

fun mapKimetsuNoYaibaCollectionDTOToCollection(kimetsuNoYaibaCollectionDTO: KimetsuNoYaibaCollectionDTO) = KimetsuNoYaibaCollection(
    name = kimetsuNoYaibaCollectionDTO.name,
    publicationYear = kimetsuNoYaibaCollectionDTO.publicationYear,
    cards = kimetsuNoYaibaCollectionDTO.cards.map { mapKimetsuNoYaibaCardDTOToCard(it) }
)