package com.demate.movieapp.navagation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demate.movieapp.screens.HomeScreen

import com.demate.movieapp.screens.details.DetailsScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            //here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        composable(
            MovieScreens.DetailsScreen.name + "/{movieId}",
            arguments = listOf(navArgument(name = "movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            //here we pass where this should lead us to
            DetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getString("movieId")
            )
        }


    }
}