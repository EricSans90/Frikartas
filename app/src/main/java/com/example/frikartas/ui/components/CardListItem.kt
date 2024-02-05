package com.example.frikartas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.example.frikartas.R
import com.example.frikartas.domain.models.Card

/**
 * Representa un ítem de lista individual para una carta.
 *
 * @param card La carta a mostrar.
 * @param onItemClick Acción que se invoca cuando se hace clic en el ítem.
 */
@Composable
fun CardListItem(
    card: Card,
    // Recibe el id de la carta
    onItemClick: (Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            // Usa el id de la carta para el evento onClick
            .clickable(onClick = { onItemClick(card.cardId) })
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            // Imagen de la carta
            CardImage(
                imageUrl = card.urlImages.firstOrNull() ?: "",
                imageHeight = 100.dp,
                imageWidth = 100.dp,
                //onImageClick = { /* Acción al clickar */ }
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                // Nombre y rareza
                Text(text = "${card.name} - ${card.rarity}",
                    fontFamily = FontFamily(Font(R.font.onepiecefont)),
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Precio y disponibilidad
                Text(text = "Price: ${card.price}€ - ${if (card.stock > 0) "In Stock" else "Out of Stock"}")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}