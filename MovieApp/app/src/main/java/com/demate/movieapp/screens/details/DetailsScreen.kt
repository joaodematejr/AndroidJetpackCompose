package com.demate.movieapp.screens.details

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, string: String?) {
    // Details Screen

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                title = {
                    Text("Movie")
                },
                navigationIcon = {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) { it ->
        it.calculateTopPadding()
        Text("Details Screen$string", style = MaterialTheme.typography.bodyLarge)
    }

    //Surface(
    //    modifier = Modifier
    //        .fillMaxHeight()
    //        .fillMaxWidth()
    //) {
    //    Column(
    //        horizontalAlignment = Alignment.CenterHorizontally,
    //        verticalArrangement = Arrangement.Center
    //    ) {
    //        Text("Details Screen$string", style = MaterialTheme.typography.bodyLarge)
    //        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
    //        Button(onClick = { navController.popBackStack() }) {
    //            Text("Go Back")
    //        }
    //    }
//
    //}

}