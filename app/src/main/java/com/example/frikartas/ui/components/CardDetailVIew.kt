package com.example.frikartas.ui.components

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.request.RequestOptions
import com.example.frikartas.R
import com.example.frikartas.domain.models.Card
import com.example.frikartas.ui.theme.BluePers
import com.example.frikartas.ui.theme.BlueTopAppBAr
import com.example.frikartas.ui.theme.Discount

@Composable
fun CardDetailView(card: Card,
                   isFavorite: Boolean,
                   onFavoriteChange: (Boolean) -> Unit,
                   onItemClick: (String, Int) -> Unit
) {
    // Estado para alternar entre imágenes
    var imageIndex by rememberSaveable { mutableStateOf(0) }
    var detailsVisible by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Espacio entre TopAppBar e imagen
        Spacer(modifier = Modifier.height(8.dp))

        // Imagen de la carta
        card.urlImages.getOrNull(imageIndex)?.let { imageUrl ->
            CardImage(
                imageUrl = imageUrl,
                imageHeight = 375.dp,
                imageWidth = 275.dp,
                onImageClick = { imageIndex = (imageIndex + 1) % card.urlImages.size } // Manejar clics en la imagen
            )
        }

        // Espacio entre imagen y texto
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween){
            // Nombre de la carta
            Text(
                text = card.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.onepiecefont)),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            //icono favorito animado
            //Cuando haga el proyecto de final de grado esta info la pasaré a la BD
            // o haré algo con ella de moemnto sirve para ver la animación
            FavoriteCardIcon(
                isFavorite = isFavorite,
                onFavoriteChange = onFavoriteChange
            )
        }


        // Rareza de la carta
        Row(){
            Text(
                text = "Rarity: ",
                color = BluePers,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "${card.rarity}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Precio y disponibilidad
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price: ",
                fontWeight = FontWeight.Bold,
                color = BluePers,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${card.price}€ ",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "To Stock",
                tint = if (card.stock > 0) BluePers else Color.Red,
            )

            StockInfo(card)
        }
        // Información del descuento si aplica
        Row(verticalAlignment = Alignment.CenterVertically){
            if (card.discount) {
                Text(
                    text = "Discount: ${card.discountAmount}%",
                    color = Discount,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // Detalles de la carta
        CardDetailsVisibility(visible = detailsVisible, card = card)


        Button(
            colors = ButtonDefaults.buttonColors(containerColor = BlueTopAppBAr),
            onClick = { detailsVisible = !detailsVisible },
            modifier = Modifier.padding(16.dp),

        ) {
            Text(text = if (detailsVisible) "Hide Details" else "Show Details")
        }


    }
}


@Composable
fun CardDetailsVisibility(visible: Boolean, card: Card) {
    // Define la animación de visibilidad
    val enterTransition = fadeIn() + expandVertically()
    val exitTransition = fadeOut() + shrinkVertically()

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Descripción de la carta + tamaño + lenguajes
            if (card.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Row(){
                        Text(
                            text = "Description: ",
                            fontWeight = FontWeight.Bold,
                            color = BluePers,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "${card.description}.",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(){
                        Text(
                            text = "Size: ",
                            fontWeight = FontWeight.Bold,
                            color = BluePers,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "${card.size}.",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(){
                        Text(
                            text = "Languages: ",
                            fontWeight = FontWeight.Bold,
                            color = BluePers,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "${card.languages.joinToString(", ") { it.displayName }}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun StockInfo(card: Card) {
    Box(contentAlignment = Alignment.TopEnd) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (card.stock > 0) "In Stock" else "Out of Stock",
                color = if (card.stock > 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
            // Otros elementos
        }

        // Badge con el stock
        if (card.stock > 0) {
            StockBadge(stock = card.stock)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockBadge(stock: Int) {
    BadgedBox(badge = {
        Badge { Text(text = stock.toString()) }
    }) {
        // Aquí puedes poner un icono si quieres que el badge esté asociado a un icono
    }
}

@Composable
fun CardImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 200.dp,
    imageWidth: Dp = 250.dp,
    onImageClick: (() -> Unit)? = null // Opcional, si quieres manejar clics en la imagen
) {
    val processedImageUrl = generateUrl(imageUrl)
    val imageHeightPx = imageHeight.toPx().toInt()
    val imageWidthPx = imageWidth.toPx().toInt()

    // AndroidView con ImageView para usar Glide
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    imageWidthPx,
                    imageHeightPx
                )
                clipToOutline = true // Para mantener la forma del ImageView según el outline
            }
        },
        update = { imageView ->
            // Cargar la imagen con Glide
            Glide.with(imageView.context)
                .load(processedImageUrl)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .into(imageView)

            // Opcional: Manejar clics en la imagen
            onImageClick?.let { clickAction ->
                imageView.setOnClickListener { clickAction() }
            }
        },
        modifier = modifier
    )
}

// Función para convertir la URL de Google Drive a una URL descargable
fun generateUrl(s: String): String {
    val p = s.split("/")
    return "https://drive.google.com/uc?export=download&id=" + p[5]
}

@Composable
// Función de extensión para convertir Dp a Px
fun Dp.toPx() = this.value * LocalContext.current.resources.displayMetrics.density

