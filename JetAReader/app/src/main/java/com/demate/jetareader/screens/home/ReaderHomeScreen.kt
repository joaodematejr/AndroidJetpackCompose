package com.demate.jetareader.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.demate.jetareader.components.FABContent
import com.demate.jetareader.components.ListCard
import com.demate.jetareader.components.ReaderAppBar
import com.demate.jetareader.components.TitleSection
import com.demate.jetareader.model.MBook
import com.demate.jetareader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeContent(navController: NavController) {
    val listOfBooks = listOf<MBook>(
        MBook("1", "Title", "Author", "Description"),
        MBook("2", "Title", "Author", "Description"),
        MBook("3", "Title", "Author", "Description"),
        MBook("4", "Title", "Author", "Description"),
        MBook("5", "Title", "Author", "Description"),
        MBook("6", "Title", "Author", "Description"),
        MBook("7", "Title", "Author", "Description"),
        MBook("8", "Title", "Author", "Description"),
        MBook("9", "Title", "Author", "Description"),
        MBook("10", "Title", "Author", "Description"),
        MBook("11", "Title", "Author", "Description"),
        MBook("12", "Title", "Author", "Description"),
        MBook("13", "Title", "Author", "Description"),
        MBook("14", "Title", "Author", "Description"),
        MBook("15", "Title", "Author", "Description"),
        MBook("16", "Title", "Author", "Description"),
        MBook("17", "Title", "Author", "Description"),
        MBook("18", "Title", "Author", "Description"),
        MBook("19", "Title", "Author", "Description"),
        MBook("20", "Title", "Author", "Description"),
    )
    val currentUserName =
        if (!FirebaseAuth.getInstance().currentUser?.displayName.isNullOrEmpty()) {
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
        } else {
            "Anonymous"
        }
    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Reading Right Now")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = Color.Blue.copy(alpha = 0.8f)
                )
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                HorizontalDivider()
            }
        }
        ReadingRightNowArea(books = listOfBooks, navController = navController)
        TitleSection(label = "Reading List")
        BoolListArea(listOfBooks = listOfBooks, navController = navController)

    }
}

@Composable
fun BoolListArea(listOfBooks: List<MBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBooks) {
        //Todo: Navigate to Book Details
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit = {}) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBooks) {
            ListCard(book = book) {
                onCardPressed(it)
            }
        }
    }
}


@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = {
            ReaderAppBar(title = "Jet.A.Reader", showProfile = true, navController = navController)
        },
        floatingActionButton = {
            FABContent {
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }) {
        it.calculateBottomPadding()
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController)
        }
    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard(books[0]) {

    }
}
