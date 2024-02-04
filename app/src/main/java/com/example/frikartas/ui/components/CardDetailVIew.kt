package com.example.frikartas.ui.components

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.request.RequestOptions
import com.example.frikartas.domain.models.Card

@Composable
fun CardDetailView(card: Card) {
    // Estado para alternar entre imágenes
    var imageIndex by rememberSaveable { mutableStateOf(0) }

    // Altura y anchura de la imagen
    val imageHeightPx = 375.dp.toPx().toInt()
    val imageWidthPx = 250.dp.toPx().toInt()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        card.urlImages.getOrNull(imageIndex)?.let { imageUrl ->
            val processedImageUrl = generateUrl(imageUrl)
            // AndroidView con ImageView para usar Glide
            AndroidView(factory = { context ->
                ImageView(context).apply {
                    // Ajustes del ImageView
                    layoutParams = ViewGroup.LayoutParams(
                        imageWidthPx,
                        imageHeightPx
                    )
                    clipToOutline = true
                }
            }, update = { imageView ->
                // Cargar la imagen con Glide
                Glide.with(imageView.context)
                    .load(processedImageUrl)
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            //.transform(Rotate(90))
                    )
                    .into(imageView)

                // Manejar clics en la imagen
                imageView.setOnClickListener {
                    imageIndex = (imageIndex + 1) % card.urlImages.size
                }
            })
        }

        // Espacio entre imagen y texto
        Spacer(modifier = Modifier.height(8.dp))

        // Nombre de la carta
        Text(
            text = card.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Rareza de la carta
        Text(
            text = "Rarity: ${card.rarity}",
            style = MaterialTheme.typography.bodyLarge
        )

        // Precio y disponibilidad
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price: ${card.price}€ ",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = if (card.stock > 0) "In Stock" else "Out of Stock",
                color = if (card.stock > 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Descripción de la carta
        if (card.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${card.description}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Información del SKU y descuento si aplica
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SKU: ${card.SKU}",
                style = MaterialTheme.typography.bodySmall
            )

            if (card.discount) {
                Text(
                    text = "Discount: ${card.discountAmount}%",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Tamaño y lenguajes disponibles
        Text(
            text = "Size: ${card.size}",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Languages: ${card.languages.joinToString(", ") { it.displayName }}",
            style = MaterialTheme.typography.bodyMedium
        )
    }

    
}

@Composable
// Función de extensión para convertir Dp a Px
fun Dp.toPx() = this.value * LocalContext.current.resources.displayMetrics.density

fun generateUrl(s: String): String {
    val p = s.split("/")
    return "https://drive.google.com/uc?export=download&id=" + p[5]
}


