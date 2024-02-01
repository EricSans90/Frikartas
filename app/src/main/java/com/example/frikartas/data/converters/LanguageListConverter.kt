package com.example.frikartas.data.converters

import com.example.frikartas.data.models.LanguageDTO
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class LanguageListConverter : AttributeConverter<List<LanguageDTO>, String> {
    override fun convertToDatabaseColumn(attribute: List<LanguageDTO>?): String {
        return attribute?.joinToString(",") { it.displayName } ?: ""
    }

    override fun convertToEntityAttribute(dbData: String?): List<LanguageDTO> {
        return dbData?.split(",")?.map { LanguageDTO.valueOf(it.trim().toUpperCase()) } ?: listOf()
    }
}