package com.demate.jetareader.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.demate.jetareader.components.ReaderAppBar
import com.demate.jetareader.model.MBook
import com.demate.jetareader.navigation.ReaderScreens
import com.demate.jetareader.screens.home.HomeScreenViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderStatsScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    var books: List<MBook>
    val currentUser = FirebaseAuth.getInstance().currentUser

    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Book Stats",
                icon = Icons.Default.Backup,
                showProfile = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        },
    ) {
        it.calculateTopPadding()
        Surface() {
            books = if (!viewModel.data.value.data.isNullOrEmpty()) {
                viewModel.data.value.data!!.filter { mBook ->
                    (mBook.userId == currentUser?.uid)
                }
            } else {
                emptyList()
            }
            Column {
                Row() {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .padding(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Backup,
                            contentDescription = "Backup",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                        Text(text = "Books: ${books.size}")
                        Text(text = "Books: ${books.size}")
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    shape = CircleShape,
                ) {
                    val readBooksList: List<MBook> =
                        if (!viewModel.data.value.data.isNullOrEmpty()) {
                            books.filter { mBook ->
                                (mBook.userId == currentUser?.uid) && (mBook.finishedReading != null)
                            }
                        } else {
                            emptyList()
                        }

                    val readingBooks = books.filter { mBook ->
                        (mBook.userId == currentUser?.uid) && (mBook.finishedReading == null)
                    }

                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Read Books: ${readBooksList.size}")
                            if (readBooksList.isNotEmpty()) {
                                Text(
                                    text = "Last Read: ${readBooksList.last().finishedReading}",
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            //if (book.rating >= 4) {
                            //    Text(text = "Highly Rated: ${book.rating}")
                            //    Icon()
                            //}
                        }
                        Text(text = "Read Books: ${readBooksList.size}")
                        HorizontalDivider()
                        Text(text = "Reading Books: ${readingBooks.size}")
                    }
                }
                if (viewModel.data.value.loading == true) {
                    LinearProgressIndicator()
                } else {
                    HorizontalDivider()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        val readBooks: List<MBook> =
                            if (!viewModel.data.value.data.isNullOrEmpty()) {
                                viewModel.data.value.data!!.filter { mBook ->
                                    (mBook.userId == currentUser?.uid) && (mBook.finishedReading != null)
                                }
                            } else {
                                emptyList()
                            }
                        items(items = readBooks) { book ->
                            BookRow(book = book, navController = navController)
                            Divider()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BookRow(
    book: MBook,
    navController: NavController,
) {
    Card(modifier = Modifier
        .clickable {
            navController.navigate(ReaderScreens.DetailScreen.name + "/${book.id}")
        }
        .fillMaxWidth()
        .height(100.dp)
        .padding(3.dp),
        shape = RectangleShape) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {

            val imageUrl: String = if (book.photoUrl.isNullOrEmpty())
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            else {
                book.photoUrl.toString()
            }
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "book image",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp),
            )

            Column {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(
                    text = "Author: ${book.authors}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Date: ${book.publishedDate}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "${book.categories}",
                    overflow = TextOverflow.Clip,
                    fontStyle = FontStyle.Italic,
                    softWrap = true,
                    style = MaterialTheme.typography.bodySmall
                )


            }

        }

    }

}
