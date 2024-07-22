package com.demate.jetareader.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.demate.jetareader.components.FABContent
import com.demate.jetareader.components.ReaderAppBar
import com.demate.jetareader.components.TitleSection
import com.demate.jetareader.model.MBook
import com.demate.jetareader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeContent(navController: NavController) {
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

            }
        }) {
        it.calculateBottomPadding()
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController)
        }
    }
}

@Composable
fun ListCard(
    book: MBook = MBook("0", "Title", "Author", "Description"),
    onPressDetails: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp
    Card(
        shape = RoundedCornerShape(29.dp),
        modifier = Modifier
            .padding(15.dp)
            .height(240.dp)
            .width(202.dp)
            .clickable { book.title?.let { onPressDetails.invoke(it) } }
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberAsyncImagePainter(model = ""),
                    contentDescription = "",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier.padding(bottom = 1.dp),
                    )
                    BookRating(score = 3.5)
                }
            }
            Text(
                text = book.title!!,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(4.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.author!!,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun BookRating(score: Double = 3.5) {
    Surface(
        modifier = Modifier
            .height(70.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color.Gray,
        shadowElevation = 4.dp,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = score.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

        }
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {

}
