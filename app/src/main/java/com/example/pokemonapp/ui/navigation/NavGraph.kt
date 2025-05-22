package com.example.pokemonapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapp.ui.screen.detail.DetailScreen
import com.example.pokemonapp.ui.screen.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
        composable(
            "detail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            DetailScreen(pokemonName = pokemonName)
        }
    }
}
