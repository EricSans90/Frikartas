package com.example.frikartas.data.models


enum class CollectTypeDTO(val displayName: String) {
    ONEPIECE("One Piece"), KIMETSUNOYAIBA("Kimetsu No Yaiba");

    //Muestro los nombres en minúsculas con la primera letra en Mayus
    override fun toString(): String {
        return displayName
    }
}