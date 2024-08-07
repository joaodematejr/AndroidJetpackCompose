package com.demate.jetareader.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demate.jetareader.screens.ReaderSplashScreen
import com.demate.jetareader.screens.details.BookDetailsScreen
import com.demate.jetareader.screens.home.Home
import com.demate.jetareader.screens.home.HomeScreenViewModel
import com.demate.jetareader.screens.login.ReaderLoginScreen
import com.demate.jetareader.screens.search.BooksSearchViewModel
import com.demate.jetareader.screens.search.ReaderBookSearchScreen
import com.demate.jetareader.screens.stats.ReaderStatsScreen
import com.demate.jetareader.screens.update.BookUpdateScreen


@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            Home(navController = navController, viewModel = homeViewModel)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderStatsScreen(navController = navController, viewModel = homeViewModel)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<BooksSearchViewModel>()
            ReaderBookSearchScreen(navController = navController, viewModel = searchViewModel)
        }

        val detailsName = ReaderScreens.DetailScreen.name
        composable("$detailsName/{bookId}", arguments = listOf(navArgument("bookId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId")?.let { bookId ->
                BookDetailsScreen(navController = navController, bookId = bookId)
            }
        }

        val updateName = ReaderScreens.UpdateScreen.name
        composable("$updateName/{bookItemId}", arguments = listOf(navArgument("bookItemId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookItemId")?.let { bookItemId ->
                BookUpdateScreen(navController = navController, bookItemId = bookItemId)
            }
        }
    }
}