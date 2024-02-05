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

/**
 * Muestra los detalles de una carta específica, incluyendo imágenes, nombre, rareza, precio,
 * disponibilidad, descripción y detalles adicionales.
 *
 * @param card La carta a mostrar.
 * @param isFavorite Indica si la carta está marcada como favorita.
 * @param onFavoriteChange Acción que se invoca cuando se cambia el estado de favorito.
 * @param onItemClick Acción que se invoca cuando se hace clic en algún elemento de la vista detallada.
 */
@Composable
fun CardDetailView(card: Card,
                   isFavorite: Boolean,
                   onFavoriteChange: (Boolean) -> Unit,
                   onItemClick: (String, Int) -> Unit
) {
    var imageIndex by rememberSaveable { mutableStateOf(0) }
    var detailsVisible by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        card.urlImages.getOrNull(imageIndex)?.let { imageUrl ->
            CardImage(
                imageUrl = imageUrl,
                imageHeight = 375.dp,
                imageWidth = 275.dp,
                onImageClick = { imageIndex = (imageIndex + 1) % card.urlImages.size }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Sección del nombre y el icono de favorito
        NameAndFavoriteSection(card, isFavorite, onFavoriteChange)

        // Sección de rareza, precio y disponibilidad
        RarityPriceStockSection(card)

        // Sección de detalles adicionales
        DetailsSection(card, detailsVisible, { detailsVisible = !detailsVisible })
    }
}

/**
 * Muestra la sección del nombre de la carta y el ícono de favorito.
 *
 * @param card La carta cuyos detalles se van a mostrar.
 * @param isFavorite Indica si la carta está marcada como favorita.
 * @param onFavoriteChange Acción que se invoca cuando se cambia el estado de favorito.
 */
@Composable
fun NameAndFavoriteSection(card: Card, isFavorite: Boolean, onFavoriteChange: (Boolean) -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = card.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.onepiecefont)),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        FavoriteCardIcon(
            isFavorite = isFavorite,
            onFavoriteChange = onFavoriteChange
        )
    }
}

/**
 * Muestra la sección de rareza, precio y disponibilidad de la carta.
 *
 * @param card La carta cuyos detalles se van a mostrar.
 */
@Composable
fun RarityPriceStockSection(card: Card) {
    Row() {
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

    Row(verticalAlignment = Alignment.CenterVertically) {
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

    if (card.discount) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Discount: ${card.discountAmount}%",
                color = Discount,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/**
 * Muestra la sección de detalles adicionales de la carta.
 *
 * @param card La carta cuyos detalles se van a mostrar.
 * @param detailsVisible Controla la visibilidad de los detalles adicionales.
 * @param onToggleDetails Acción que se invoca para mostrar u ocultar los detalles adicionales.
 */
@Composable
fun DetailsSection(card: Card, detailsVisible: Boolean, onToggleDetails: () -> Unit) {
    CardDetailsVisibility(visible = detailsVisible, card = card)
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = BlueTopAppBAr),
        onClick = onToggleDetails,
        modifier = Modifier.padding(16.dp),
    ) {
        Text(text = if (detailsVisible) "Hide Details" else "Show Details")
    }
}

/**
 * Muestra u oculta los detalles adicionales de la carta.
 *
 * @param visible Controla la visibilidad de los detalles adicionales.
 * @param card La carta cuyos detalles adicionales se van a mostrar.
 */
@Composable
fun CardDetailsVisibility(visible: Boolean, card: Card) {
    val enterTransition = fadeIn() + expandVertically()
    val exitTransition = fadeOut() + shrinkVertically()

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            if (card.description.isNotEmpty()) {
                Row(horizontalArrangement = Arrangement.Center){
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
                Row(horizontalArrangement = Arrangement.Center){
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
                Row(horizontalArrangement = Arrangement.Center){
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

/**
 * Muestra la información de stock de la carta.
 *
 * @param card La carta cuya información de stock se va a mostrar.
 */
@Composable
fun StockInfo(card: Card) {
    Box(contentAlignment = Alignment.TopEnd) {
        Text(
            text = if (card.stock > 0) "In Stock" else "Out of Stock",
            color = if (card.stock > 0) Color.Green else Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
        if (card.stock > 0) {
            StockBadge(stock = card.stock)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
/**
 * Muestra un badge con el número de items en stock.
 *
 * @param stock El número de items en stock.
 */
@Composable
fun StockBadge(stock: Int) {
    BadgedBox(badge = {
        Badge { Text(text = stock.toString()) }
    }) {
        // Icono relacionado con el stock puede ser agregado aquí
    }
}

/**
 * Muestra la imagen de la carta.
 *
 * @param imageUrl URL de la imagen a mostrar.
 * @param modifier Modificador para personalizar el diseño de la imagen.
 * @param imageHeight Altura de la imagen.
 * @param imageWidth Ancho de la imagen.
 * @param onImageClick Acción que se invoca cuando se hace clic en la imagen.
 */
@Composable
fun CardImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 200.dp,
    imageWidth: Dp = 250.dp,
    onImageClick: (() -> Unit)? = null // Opcional
) {
    val processedImageUrl = generateUrl(imageUrl)
    val imageHeightPx = imageHeight.toPx().toInt()
    val imageWidthPx = imageWidth.toPx().toInt()

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
            Glide.with(imageView.context)
                .load(processedImageUrl)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .into(imageView)
            onImageClick?.let { clickAction ->
                imageView.setOnClickListener { clickAction() }
            }
        },
        modifier = modifier
    )
}

/**
 * Convierte una URL de Google Drive en una URL descargable.
 *
 * @param s La URL original de Google Drive.
 * @return La URL transformada para descarga directa.
 */
fun generateUrl(s: String): String {
    val p = s.split("/")
    return "https://drive.google.com/uc?export=download&id=" + p[5]
}

@Composable
        /**
         * Convierte unidades Dp a píxeles.
         *
         * @return El valor en píxeles correspondiente a las unidades Dp.
         */
fun Dp.toPx() = this.value * LocalContext.current.resources.displayMetrics.density
