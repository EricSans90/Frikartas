package com.example.frikartas.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color

//Animación del corazón al darle favorito a la carta
// (no hay lógica de guradar eso en la BD todavía, lo haré en el proyecto de final de grado)

/**
 * Muestra un ícono de corazón que puede ser marcado o desmarcado como favorito.
 *
 * @param isFavorite Indica si el ícono está marcado como favorito.
 * @param onFavoriteChange Acción que se invoca cuando se cambia el estado de favorito.
 */
@Composable
fun FavoriteCardIcon(isFavorite: Boolean, onFavoriteChange: (Boolean) -> Unit) {
    val scale: Float by animateFloatAsState(
        targetValue = if (isFavorite) 1.5f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    IconButton(onClick = { onFavoriteChange(!isFavorite) }) {
        Icon(
            imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.scale(scale), // Animación de escala
            tint = if (isFavorite) Color.Red else Color.Black
        )
    }
}