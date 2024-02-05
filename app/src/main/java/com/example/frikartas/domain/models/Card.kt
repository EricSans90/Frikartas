package com.example.frikartas.domain.models

import com.example.frikartas.data.models.LanguageDTO

/**
 * Representa una carta dentro de una colección.
 *
 * @property cardId Identificador único para la carta.
 * @property name Nombre de la carta.
 * @property tags Lista de etiquetas asociadas con la carta.
 * @property rarity Nivel de rareza de la carta.
 * @property stock Cantidad disponible en inventario.
 * @property price Precio base de la carta.
 * @property discount Indica si la carta tiene aplicado un descuento.
 * @property discountAmount Monto del descuento aplicado a la carta, por defecto es 20%.
 * @property precioDiscount Precio final de la carta después de aplicar el descuento.
 * @property SKU Código de almacenamiento de la carta.
 * @property urlImages Lista de URLs a las imágenes de la carta.
 * @property description Descripción detallada de la carta.
 * @property languages Idiomas en los que está disponible la carta.
 * @property size Tamaño físico de la carta.
 * @property collection Colección a la que pertenece la carta.
 */
data class Card (
    val cardId: Int,
    val name: String,
    val tags: List<String>,
    val rarity: String,
    val stock: Int,
    val price: Float,
    val discount: Boolean,
    //De momento discount fijo 20%
    val discountAmount: Float = 20.0F,
    val precioDiscount: Float = if(discount){(100-discountAmount)*price} else price,
    val SKU: String,
    val urlImages: List<String>,
    val description: String,
    val languages: List<LanguageDTO>,
    val size: String,
    val collection: Collection
)

