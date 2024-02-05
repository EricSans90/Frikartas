package com.example.frikartas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.frikartas.ui.screens.MainScreen
import com.example.frikartas.ui.screens.CollectionDetailScreen
import com.example.frikartas.ui.screens.CardDetailScreen
import com.example.frikartas.ui.screens.CardListScreen
import com.example.frikartas.ui.screens.CollectionListScreen
import com.example.frikartas.ui.theme.BlueBottomAppBAr
import com.example.frikartas.ui.theme.BlueDrawer
import com.example.frikartas.ui.theme.BlueTopAppBAr
import com.example.frikartas.ui.theme.FrikartasTheme
import com.example.frikartas.ui.viewmodels.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Actividad principal de Frikartas. Configura y muestra la navegación principal de la aplicación, incluyendo el drawer, la barra de navegación superior y la barra de navegación inferior.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val cardViewModel: CardViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrikartasTheme {
                // Configuración y estado del drawer (menú lateral)
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                // Estructura principal de la aplicación
                ModalNavigationDrawer(
                    drawerContent = {
                        Column(Modifier.fillMaxWidth()) {
                            DrawerContent(drawerItems, drawerState, scope, navController)
                        }
                    },
                    drawerState = drawerState
                ) {
                    // Estructura de la barra superior, barra inferior y contenido principal
                    Scaffold(
                        topBar = {
                            AppTopBar(
                                title = "Frikartas",
                                onNavigationIconClick = {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                },
                                navController = navController,

                            )
                        },
                        bottomBar = { AppBottomBar(navController, cardViewModel) }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier.padding(innerPadding)
                                .fillMaxSize(),
                            color = Color.DarkGray
                        ) {
                            FrikartasNavigation(navController)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Define la navegación de la aplicación. Configura las rutas y las pantallas asociadas a cada ruta.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 */
@Composable
fun FrikartasNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(onNavigate = { navController.navigate(it) })
        }
        composable("collectionList") {
            CollectionListScreen(navController)
        }
        composable("collectionDetail/{collectionId}") { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getString("collectionId")?.toIntOrNull() ?: -1
            CollectionDetailScreen(navController, collectionId)
        }
        composable("cardDetail/{cardId}") { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull() ?: -1
            CardDetailScreen(navController, cardId)
        }
        composable("cardList/{collectionId}") { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getString("collectionId")?.toIntOrNull() ?: -1
            CardListScreen(navController, collectionId)
        }
    }
}

/**
 * Representa un elemento en el drawer (menú lateral).
 *
 * @param title Título del elemento.
 * @param selectedIcon Icono cuando el elemento está seleccionado.
 * @param unselectedIcon Icono cuando el elemento no está seleccionado.
 */
data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    //si hacen falta badges en el Drawer
    // val badgeCount: Any = 0
}

/**
 * Define los elementos a mostrar en el drawer (menú lateral).
 */
val drawerItems = listOf(
    DrawerItem(
        title = "Main Screen",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    DrawerItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
    ),
    DrawerItem(
        title = "Apertura App",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
    ),

)

/**
 * Contenido del drawer (menú lateral). Muestra los elementos del menú y maneja las acciones de selección.
 *
 * @param drawerItems Lista de elementos a mostrar en el drawer.
 * @param drawerState Estado del drawer para controlar su apertura y cierre.
 * @param scope Alcance de la corrutina para ejecutar acciones del drawer.
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 * @param drawerBackgroundColor Color de fondo del drawer.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    drawerItems: List<DrawerItem>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController,
    drawerBackgroundColor: Color = BlueDrawer
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val context = LocalContext.current

    ModalDrawerSheet (modifier = Modifier
        .background(drawerBackgroundColor)){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(drawerBackgroundColor)
        ) {
            drawerItems.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = { Text(item.title, color = Color.Black) },
                    selected = index == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = index
                        scope.launch { drawerState.close() }

                        if (index == 0) {
                            navController.navigate("mainScreen") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        } else if (index == 2){
                            // Crear el Intent para abrir un navegador
                            val openURLIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                            context.startActivity(openURLIntent)
                        }

                        Toast.makeText(context, "${item.title} clicked", Toast.LENGTH_SHORT).show()
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            tint = Color.Black
                        )
                    },
                    modifier = Modifier.background(drawerBackgroundColor),  // Fondo de los items
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(drawerBackgroundColor)  // Fondo del contenedor del logo
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoepictransp),
                    contentDescription = "Logo Frikartas",
                    modifier = Modifier.size(500.dp)
                )
            }
        }
    }
}

/**
 * Barra superior de la aplicación. Muestra el título de la aplicación y botones de acción.
 *
 * @param title Título a mostrar en la barra superior.
 * @param onNavigationIconClick Acción a realizar cuando se hace clic en el icono de navegación.
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onNavigationIconClick: () -> Unit,
    navController: NavHostController
) {
    // Observa los cambios en el stack de navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val canPop = navBackStackEntry != null && navController.previousBackStackEntry != null

    TopAppBar(
        title = { Text(text = title,
            color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.White)
            }
        },
        actions = {
            if (canPop) { // Muestra el botón de atrás solo si puedes retroceder en el stack
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White)
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlueTopAppBAr),
    )
}

/**
 * Barra inferior de la aplicación. Muestra acciones rápidas para el usuario.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 * @param viewModel ViewModel asociado a la barra inferior para gestionar la lógica de negocio.
 */
@Composable
fun AppBottomBar(navController: NavHostController, viewModel: CardViewModel) {
BottomAppBar(
actions = {
    Row(horizontalArrangement = Arrangement.SpaceBetween){
        Spacer(Modifier.weight(0.5f))
        IconButton(onClick = {// Obtén una colección al azar
            viewModel.getRandomCollectionId()?.let { randomCollectionId ->
                navController.navigate("collectionDetail/$randomCollectionId")
            } ?: run {
                println("Collections weren't found")
            }
        }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
                //modifier= Modifier.background(Color.Red)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Random Collection",
                    tint = Color.White
                )
                Text(
                    text = "Random",
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = {
            viewModel.getRandomDiscountedCardId()?.let { randomCardId ->
                navController.navigate("cardDetail/$randomCardId")
            } ?: run {
                println("Cards weren't found")
            }}) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Default.Star,
                    contentDescription = "Discounted Promotion Random Card",
                    tint = Color.White)
                Text(text = "Promo",
                    color = Color.White,
                    fontSize = 10.sp)
            }
        }
        Spacer(Modifier.weight(0.5f))
    }
},
containerColor = BlueBottomAppBAr,
)
}

/**
 * Vista previa de un componente Composable.
 */
@Preview
@Composable
fun SimpleComposablePreview() {
    AppBottomBar2()
}

/**
 * Otra variante de la barra inferior para propósitos de vista previa.
 * Es una ayuda para la edición de la BottomAppBAr
 */
@Composable
fun AppBottomBar2() {
    BottomAppBar(
        actions = {
            Row(horizontalArrangement = Arrangement.SpaceBetween){
                Spacer(Modifier.weight(0.5f))
                IconButton(onClick = {
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                        //modifier= Modifier.background(Color.Red)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Random Collection",
                            tint = Color.White
                        )
                        Text(
                            text = "Collections",
                            color = Color.White,
                            fontSize = 9.sp
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(imageVector = Icons.Default.Star,
                            contentDescription = "Discounted Random Card",
                            tint = Color.White)
                        Text(text = "Discounted",
                            color = Color.White,
                            fontSize = 10.sp)
                    }
                }
                Spacer(Modifier.weight(0.5f))
            }
        },
        containerColor = BlueBottomAppBAr,
    )
}