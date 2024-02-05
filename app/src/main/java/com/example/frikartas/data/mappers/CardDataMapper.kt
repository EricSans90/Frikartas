package com.example.frikartas.data.mappers

import com.example.frikartas.data.models.CardDTO
import com.example.frikartas.data.models.CollectionDTO
import com.example.frikartas.domain.models.Card
import com.example.frikartas.domain.models.Collection

/**
 * Convierte un CardDTO a un objeto de dominio Card.
 *
 * Esta función toma un CardDTO que proviene de una fuente de datos,
 * como puede ser una respuesta de API, y lo convierte en un objeto Card del dominio
 * que se puede utilizar en la lógica de la aplicación.
 *
 * @param cardDTO Objeto CardDTO que contiene la información de la carta.
 * @param collection Objeto Collection al que pertenece la carta.
 * @return Un objeto Card con datos mapeados del DTO y asociado a la colección proporcionada.
 */
fun mapCardDTOToCard(cardDTO: CardDTO, collection: Collection) = Card(
    cardId = cardDTO.cardId,
    name = cardDTO.name,
    tags = cardDTO.tags,
    rarity = cardDTO.rarity,
    stock = cardDTO.stock,
    price = cardDTO.price,
    discount = cardDTO.discount,
    SKU = cardDTO.SKU,
    urlImages = cardDTO.urlImages,
    description = cardDTO.description,
    languages = cardDTO.languages,
    size = cardDTO.size,
    collection = collection
)
