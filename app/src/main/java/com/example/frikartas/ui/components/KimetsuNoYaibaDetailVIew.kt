package com.example.frikartas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frikartas.domain.models.KimetsuNoYaibaCard

@Composable
fun KimetsuNoYaibaDetailView(kimetsuNoYaibaCard: KimetsuNoYaibaCard) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${kimetsuNoYaibaCard.name}")
        Text(text = "Rarity: ${kimetsuNoYaibaCard.rarity}")
        // otros detalles de la carta
    }
}