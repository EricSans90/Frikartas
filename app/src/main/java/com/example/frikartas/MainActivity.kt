package com.example.frikartas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frikartas.ui.screens.KimetsuNoYaibaDetailScreen
import com.example.frikartas.ui.screens.KimetsuNoYaibaListScreen
import com.example.frikartas.ui.screens.MainScreen
import com.example.frikartas.ui.screens.OnePieceDetailScreen
import com.example.frikartas.ui.screens.OnePieceListScreen
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
        composable("kimetsuNoYaibaList") {
            KimetsuNoYaibaListScreen(navController)
        }
        composable("kimetsuNoYaibaDetail/{cardName}") { backStackEntry ->
            val cardName = backStackEntry.arguments?.getString("cardName") ?: ""
            KimetsuNoYaibaDetailScreen(navController, cardName)
        }
        composable("onePieceList") {
            OnePieceListScreen(navController)
        }
        composable("onePieceDetail/{cardName}") { backStackEntry ->
            val cardName = backStackEntry.arguments?.getString("cardName") ?: ""
            OnePieceDetailScreen(navController, cardName)
        }
    }
}