package com.example.frikartas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import com.example.frikartas.ui.theme.BlueBottomAppBAr
import com.example.frikartas.ui.theme.BluePers

/**
 * Representa la vista detallada de una colección específica.
 *
 * @param collection La colección a mostrar.
 * @param onItemClick Acción que se invoca cuando se hace clic en un ítem de la colección.
 */
@Composable
fun CollectionDetailView(collection: Collection, onItemClick: (String, Int) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        BorderedText(
            text = "${collection.name}",
            textColor = Color.Black,
            borderColor = Color.White,
            borderWidth = 2,
            textSize = 24
        )
        BorderedText(
            text = "${collection.publicationYear}",
            textColor = Color.Black,
            borderColor = Color.White,
            borderWidth = 1,
            textSize = 16
        )
        Divider()
        BorderedText(
            text = "Cartas en la colección:",
            textColor = BlueBottomAppBAr,
            borderColor = Color.White,
            borderWidth = 1,
            textSize = 20
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

/**
 * Crea un componente de texto con un borde personalizado.
 *
 * @param text El texto a mostrar.
 * @param textColor Color del texto.
 * @param borderColor Color del borde.
 * @param borderWidth Ancho del borde.
 * @param textSize Tamaño del texto.
 */
@Composable
fun BorderedText(
    text: String,
    textColor: Color,
    borderColor: Color,
    borderWidth: Int,
    textSize: Int
) {
    Box(modifier = Modifier.padding(borderWidth.dp)) {
        // Dibujar sombras alrededor del texto
        Text(
            text = text,
            color = borderColor,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            modifier = Modifier.offset((-borderWidth).dp, (-borderWidth).dp)
        )
        Text(
            text = text,
            color = borderColor,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            modifier = Modifier.offset((-borderWidth).dp, borderWidth.dp)
        )
        Text(
            text = text,
            color = borderColor,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            modifier = Modifier.offset(borderWidth.dp, (-borderWidth).dp)
        )
        Text(
            text = text,
            color = borderColor,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
            modifier = Modifier.offset(borderWidth.dp, borderWidth.dp)
        )

        // Dibujar el texto principal encima
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = textSize.sp,
            fontFamily = FontFamily(Font(R.font.krusyidascriptone)),
        )
    }
}