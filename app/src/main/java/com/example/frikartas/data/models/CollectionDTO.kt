package com.example.frikartas.data.models

/**
 * Objeto de Transferencia de Datos (DTO) para una colección de cartas.
 *
 * @property collectionId Identificador único de la colección.
 * @property collectType Tipo de la colección.
 * @property name Nombre de la colección.
 * @property publicationYear Año de publicación de la colección.
 * @property cards Lista de CardDTO que pertenecen a la colección.
 */
data class CollectionDTO(
    val collectionId: Int,
    val collectType: CollectTypeDTO,
    val name: String,
    val publicationYear: Int,
    val cards: List<CardDTO>
)