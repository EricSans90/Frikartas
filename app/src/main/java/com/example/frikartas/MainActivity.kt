package com.example.frikartas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frikartas.ui.screens.MainScreen
import com.example.frikartas.ui.screens.CollectionDetailScreen
import com.example.frikartas.ui.screens.CardDetailScreen
import com.example.frikartas.ui.screens.CardListScreen
import com.example.frikartas.ui.screens.CollectionListScreen
import com.example.frikartas.ui.theme.FrikartasTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrikartasTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        Column(Modifier.fillMaxWidth()){
                            DrawerContent(drawerItems, drawerState, scope)
                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = { AppTopBar("Frikartas", {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) },
                        bottomBar = { AppBottomBar() }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier.padding(innerPadding),
                            color = Color.DarkGray
                        ) {
                            FrikartasNavigation()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FrikartasNavigation() {
    val navController = rememberNavController()
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

data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    val badgeCount: Any = 0
}

val drawerItems = listOf(
    DrawerItem(
        title = "All",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    DrawerItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
    ),
    DrawerItem(
        title = "Información",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
    ),

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(drawerItems: List<DrawerItem>, drawerState: DrawerState, scope: CoroutineScope) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val contextForToast = LocalContext.current

    ModalDrawerSheet {
        drawerItems.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                    scope.launch { drawerState.close() }
                    Toast.makeText(contextForToast, "${item.title} clicked", Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                // Lógica para la insignia
            )
        }

        Image(
            painter = painterResource(id =R.drawable.frikartaslogo),
            contentDescription = "Dragon Ball",
            Modifier.size(500.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}

@Composable
fun AppBottomBar() {
    BottomAppBar {

    }
}