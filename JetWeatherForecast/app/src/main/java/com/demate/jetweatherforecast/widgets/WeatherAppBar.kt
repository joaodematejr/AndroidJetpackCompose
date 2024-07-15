package com.demate.jetweatherforecast.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demate.jetweatherforecast.model.Favorite
import com.demate.jetweatherforecast.navigation.WeatherScreens
import com.demate.jetweatherforecast.screens.favorite.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                }
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "more options")
                }
            } else Box {

            }

        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "back",
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    }
                )
            }
            if (isMainScreen) {
                val isAlreadyFavorite =
                    favoriteViewModel.favList.collectAsState().value.filter { item ->
                        (item.city === title.split(",")[0] && item.country === title.split(",")[1])
                    }
                if (isAlreadyFavorite.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite",
                        modifier = Modifier
                            .scale(0.9f)
                            .clickable {
                                val dataList = title.split(",")
                                favoriteViewModel
                                    .insertFavorite(
                                        Favorite(
                                            city = dataList[0], //city
                                            country = dataList[1] // country
                                        )
                                    )
                                    .run {
                                        showIt.value = true
                                    }
                            },
                        tint = Color.Red
                    )
                } else {
                    showIt.value = false
                    Box {}
                }
            }

            ShowToast(context = context, showIt)
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        //elevation = elevation
    )
}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if (showIt.value) {
        Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }, modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { _, text ->
                DropdownMenuItem(text = {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            "Settings" -> Icons.Default.Settings
                            else -> {
                                Icons.Default.Settings
                            }
                        }, contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(text = text, color = Color.Black, fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                when (text) {
                                    "About" -> WeatherScreens.AboutScreen.name
                                    "Favorites" -> WeatherScreens.FavoriteScreen.name
                                    "Settings" -> WeatherScreens.SettingsScreen.name
                                    else -> {
                                        "settings" //default
                                    }
                                }
                            )
                        }
                    )
                }, onClick = {
                    expanded = false
                    showDialog.value = false
                })
            }
        }

    }

}
