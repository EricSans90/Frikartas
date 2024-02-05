package com.example.frikartas.data.models

/**
 * Clase genérica para respuestas API estandarizadas.
 *
 * @param T El tipo de datos que contiene la respuesta.
 * @property status Estado de la respuesta (como "success" o "error").
 * @property message Mensaje descriptivo sobre la respuesta.
 * @property data Datos contenidos en la respuesta.
 */
data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T
)
