package com.demate.jetareader.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.demate.jetareader.components.InputField
import com.demate.jetareader.components.ReaderAppBar
import com.demate.jetareader.model.MBook
import com.demate.jetareader.navigation.ReaderScreens

@Composable
fun ReaderBookSearchScreen(navController: NavController) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Search Books",
            icon = Icons.Default.ArrowBack,
            showProfile = false,
            navController = navController
        ) {
            //navController.popBackStack()
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }
    }) {
        it.calculateBottomPadding()
        Surface() {
            Column() {
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Log.d("TAG", it)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                BookList(navController)
            }
        }
    }
}

@Composable
fun BookList(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
        items((
                (0..10).map {
                    MBook(
                        it.toString(),
                        "Title $it",
                        "Author $it",
                        "Description $it"
                    )
                }
                ).toList()) { book ->
            BookRow(book, navController)
        }

    }
}

@Composable
fun BookRow(book: MBook, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .height(100.dp)
            .padding(3.dp),
        shape = RectangleShape,
    ) {
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.Top) {
            val imageUrl = "https://picsum.photos/200/300"
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Book Image",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp)
            )
            Column {
                book.title?.let {
                    Text(
                        text = it,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                book.author?.let {
                    Text(
                        text = "Author: $it",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
        }

    }
}

@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search for books",
    onSearch: (String) -> Unit = {}
) {
    Column() {
        val searchQueryState = rememberSaveable {
            mutableStateOf("")
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }
        InputField(
            valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })
    }
}


