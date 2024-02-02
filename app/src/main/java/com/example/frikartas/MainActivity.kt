package com.example.frikartas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frikartas.ui.screens.MainScreen
import com.example.frikartas.ui.screens.CollectionDetailScreen
import com.example.frikartas.ui.screens.CardDetailScreen
import com.example.frikartas.ui.screens.CardListScreen
import com.example.frikartas.ui.theme.FrikartasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrikartasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FrikartasNavigation()
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
        composable("onePieceList") {
            CardListScreen(navController)
        }
        composable("onePieceDetail/{collectionName}/{cardName}") { backStackEntry ->
            val collectionName = backStackEntry.arguments?.getString("collectionName") ?: ""
            val cardName = backStackEntry.arguments?.getString("cardName") ?: ""
            CardDetailScreen(navController, collectionName, cardName)
        }
        composable("onePieceCollectionDetail/{collectionName}") { backStackEntry ->
            val collectionName = backStackEntry.arguments?.getString("collectionName") ?: ""
            CollectionDetailScreen(navController, collectionName)
        }
    }
}