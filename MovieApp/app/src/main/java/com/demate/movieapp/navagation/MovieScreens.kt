package com.demate.movieapp.navagation

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String): MovieScreens = when (route?.substringBefore('/')) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException()


        }

    }
}