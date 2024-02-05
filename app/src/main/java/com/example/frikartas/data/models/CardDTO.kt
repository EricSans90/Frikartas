package com.example.frikartas.data.models

/**
 * Objeto de Transferencia de Datos (DTO) para una carta.
 *
 * @property cardId Identificador único de la carta.
 * @property name Nombre de la carta.
 * @property tags Etiquetas asociadas con la carta.
 * @property rarity Rareza de la carta.
 * @property stock Cantidad en inventario.
 * @property price Precio de la carta.
 * @property discount Indica si la carta tiene descuento.
 * @property SKU Código de producto (Stock Keeping Unit).
 * @property urlImages URLs de las imágenes de la carta.
 * @property description Descripción de la carta.
 * @property languages Idiomas en los que está disponible la carta.
 * @property size Tamaño de la carta.
 */
data class CardDTO(
    val cardId: Int,
    val name: String,
    val tags: List<String>,
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    val SKU: String,
    val urlImages: List<String>,
    val description: String,
    val languages: List<LanguageDTO>,
    val size: String
)

