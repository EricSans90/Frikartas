package com.example.frikartas.data.models

enum class LanguageDTO(val displayName: String) {
    SPANISH("Spanish"), ENGLISH("English"), JAPANESE("Japanese"), CHINESE("Chinese");

    //Muestros los nombres en minúsculas con la primera letra en Mayus
    override fun toString(): String {
        return displayName
    }
}