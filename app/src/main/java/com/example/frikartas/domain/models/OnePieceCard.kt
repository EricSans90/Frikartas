package com.example.frikartas.domain.models

import com.example.frikartas.data.converters.LanguageListConverter
import com.example.frikartas.data.converters.StringListConverter
import com.example.frikartas.data.models.LanguageDTO
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Converter
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "one_piece_cards")
data class OnePieceCard (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "name")
    val name: String,

    @Convert(converter = StringListConverter::class)
    @Column(name = "tags")
    val tags: List<String>,

    @Column(name = "rarity")
    val rarity: String,

    @Column(name = "stock")
    val stock: Int,

    @Column(name = "price")
    val price: Float,

    @Column(name = "discount")
    val discount: Boolean,

    //De momento discount fijo 20%
    val discountAmount: Float = 20.0F,
    val precioDiscount: Float = if(discount){(100-discountAmount)*price} else price,

    @Column(name = "sku")
    val SKU: String,

    @Convert(converter = StringListConverter::class)
    @Column(name = "url_images")
    val urlImages: List<String>,

    @Column(name = "description")
    val description: String,

    @Convert(converter = LanguageListConverter::class)
    @Column(name = "languages")
    val languages: List<LanguageDTO>,

    @Column(name = "size")
    val size: String
)
