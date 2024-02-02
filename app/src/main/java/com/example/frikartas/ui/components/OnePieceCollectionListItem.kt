package com.example.frikartas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frikartas.domain.models.OnePieceCollection

@Composable
fun OnePieceCollectionListItem(onePieceCollection: OnePieceCollection, onItemClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 36.dp, bottomEnd = 36.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = onePieceCollection.name)
            Text(text = "Año de publicación: ${onePieceCollection.publicationYear}")
            // más detalles de la colección
        }
    }
}