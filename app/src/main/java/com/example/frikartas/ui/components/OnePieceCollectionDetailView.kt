package com.example.frikartas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frikartas.domain.models.OnePieceCollection


@Composable
fun OnePieceCollectionDetailView(onePieceCollection: OnePieceCollection, onItemClick: (collectionName: String, cardName: String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Colección: ${onePieceCollection.name}")
        Text(text = "Año de Publicación: ${onePieceCollection.publicationYear}")
        Divider()
        Text(text = "Cartas en la colección:")

        LazyColumn {
            items(onePieceCollection.cards) { card ->
                OnePieceListItem(onePieceCard = card, onItemClick = {
                    onItemClick(onePieceCollection.name, card.name)
                })
            }
        }
    }
}