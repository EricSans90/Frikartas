package com.example.frikartas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frikartas.domain.models.Collection


@Composable
fun CollectionDetailView(collection: Collection, onItemClick: (String, Int) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Colección: ${collection.name}")
        Text(text = "Año de Publicación: ${collection.publicationYear}")
        Divider()
        Text(text = "Cartas en la colección:")

        LazyColumn {
            items(collection.cards) { card ->
                CardListItem(card = card, onItemClick = {
                    onItemClick(collection.name, card.cardId)
                })
            }
        }
    }
}