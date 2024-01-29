package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.OnePieceCardDTO
import com.example.frikartas.data.models.OnePieceCollectionDTO
import com.example.frikartas.domain.models.OnePieceCard
import com.example.frikartas.domain.models.OnePieceCollection

fun mapOnePieceCardDTOToCard(onePieceCardDTO: OnePieceCardDTO) = OnePieceCard(
    name = onePieceCardDTO.name,
    urlImg = onePieceCardDTO.urlImg,
    rarity = onePieceCardDTO.rarity,
    stock = onePieceCardDTO.stock,
    precio = onePieceCardDTO.precio,
    discount = onePieceCardDTO.discount,
)

fun mapOnePieceCollectionDTOToCollection(onePieceCollectionDTO: OnePieceCollectionDTO) = OnePieceCollection(
    name = onePieceCollectionDTO.name,
    publicationYear = onePieceCollectionDTO.publicationYear,
    cards = onePieceCollectionDTO.cards.map { mapOnePieceCardDTOToCard(it) }
)