package com.example.frikartas.data.models

/**
 * Enumeración de los tipos de colección disponibles.
 *
 * @property displayName Nombre para mostrar el tipo de colección con un formato más legible.
 */
enum class CollectTypeDTO(val displayName: String) {
    ONEPIECE("One Piece"), KIMETSUNOYAIBA("Kimetsu No Yaiba");

    /**
     * Devuelve el nombre para mostrar del tipo de colección con la primera letra en mayúscula.
     */
    override fun toString(): String {
        return displayName
    }
}