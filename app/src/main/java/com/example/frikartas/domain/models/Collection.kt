package com.example.frikartas.domain.models

import com.example.frikartas.data.models.CollectTypeDTO

/**
 * Representa una colección de cartas.
 *
 * @property collectionId Identificador único para la colección.
 * @property collectType Tipo de la colección.
 * @property name Nombre de la colección.
 * @property publicationYear Año de publicación de la colección.
 * @property cards Lista de cartas que pertenecen a la colección.
 */
data class Collection(
    val collectionId: Int,
    val collectType: CollectTypeDTO,
    val name: String,
    val publicationYear: Int,
    var cards: List<Card>,
)