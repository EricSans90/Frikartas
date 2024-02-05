package com.example.frikartas.data.models

/**
 * Enumeración de los idiomas disponibles para las cartas.
 *
 * @property displayName Nombre para mostrar el idioma con un formato más legible.
 */
enum class LanguageDTO(val displayName: String) {
    SPANISH("Spanish"), ENGLISH("English"), JAPANESE("Japanese"), CHINESE("Chinese");

    /**
     * Devuelve el nombre para mostrar del idioma con la primera letra en mayúscula.
     */
    override fun toString(): String {
        return displayName
    }
}