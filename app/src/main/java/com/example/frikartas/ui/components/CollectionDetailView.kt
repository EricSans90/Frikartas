package com.example.frikartas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frikartas.R
import com.example.frikartas.domain.models.Collection


@Composable
fun CollectionDetailView(collection: Collection, onItemClick: (String, Int) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "${collection.name}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            )
        Text(text = "${collection.publicationYear}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.krusyidascripttwo)),
        )
        Divider()
        Text(text = "Cartas en la colección:",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            )
        Divider()

        LazyColumn {
            items(collection.cards) { card ->
                CardListItem(card = card, onItemClick = {
                    onItemClick(collection.name, card.cardId)
                })
            }
        }
    }
}