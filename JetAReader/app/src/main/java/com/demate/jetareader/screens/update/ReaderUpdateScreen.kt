package com.demate.jetareader.screens.update

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.demate.jetareader.components.InputField
import com.demate.jetareader.components.RatingBar
import com.demate.jetareader.components.ReaderAppBar
import com.demate.jetareader.components.RounderButton
import com.demate.jetareader.components.showToast
import com.demate.jetareader.data.DataOrException
import com.demate.jetareader.model.MBook
import com.demate.jetareader.navigation.ReaderScreens
import com.demate.jetareader.screens.home.HomeScreenViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BookUpdateScreen(
    navController: NavHostController,
    bookItemId: String,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Update book",
            icon = Icons.Default.ArrowBack,
            showProfile = false,
            navController = navController
        ) {
            navController.popBackStack()
        }
    }) {
        it.calculateBottomPadding()
        val bookInfo = produceState<DataOrException<List<MBook>, Boolean, Exception>>(
            initialValue =
            DataOrException(data = emptyList(), true, Exception(""))
        ) {
            value = viewModel.data.value
        }.value

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d("BookUpdateScreen", "BookUpdateScreen: $bookInfo")
                if (bookInfo.loading == true) {
                    LinearProgressIndicator()
                    bookInfo.loading = false
                } else {
                    Surface(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        shape = CircleShape,
                    ) {
                        ShowBookUpdate(bookInfo = viewModel.data.value, bookItemId = bookItemId)
                    }

                    ShowSimpleForm(book = viewModel.data.value.data?.first { mBook ->
                        mBook.googleBookId == bookItemId
                    }!!, navController)
                }
            }
        }
    }
}

@Composable
fun SimpleForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    defaultValue: String = "",
    onSearch: (String) -> Unit
) {
    Column() {
        val textFieldValue = rememberSaveable { mutableStateOf(defaultValue) }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(textFieldValue.value) {
            textFieldValue.value.trim().isNotEmpty()
        }

        InputField(
            modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(3.dp)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            valueState = textFieldValue,
            labelId = "Enter Your",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(textFieldValue.value.trim())
                keyboardController?.hide()
            })

    }
}

@Composable
fun ShowSimpleForm(book: MBook, navController: NavHostController) {
    val notesText = remember {
        mutableStateOf("")
    }
    val isStartedReading = remember {
        mutableStateOf(false)
    }
    val isFinishedReading = remember {
        mutableStateOf(false)
    }
    val ratingVal = remember {
        mutableIntStateOf(0)
    }
    SimpleForm(
        defaultValue = if (book.notes.toString().isNotEmpty()) book.notes.toString() else ""
    ) {
        notesText.value = it
    }
    Row(
        modifier = Modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextButton(
            onClick = {
                isStartedReading.value = true
            },
            enabled = book.startedReading == null
        ) {
            if (book.startedReading == null) {
                if (!isStartedReading.value) {
                    Text(text = "Start Reading")
                } else {
                    Text(
                        text = "Started Reading",
                        modifier = Modifier.alpha(0.6f),
                        color = Color.Red.copy(alpha = 0.6f)
                    )
                }
            } else {
                //Todo format date
                Text(
                    text = "Started on: ${book.startedReading}",
                    modifier = Modifier.alpha(0.6f),
                    color = Color.Red.copy(alpha = 0.6f)
                )

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { isFinishedReading.value = true },
            enabled = book.finishedReading == null
        ) {
            if (book.finishedReading == null) {
                if (!isFinishedReading.value) {
                    Text(text = "Mark as Read")
                } else {
                    Text(
                        text = "Finished Reading",
                        modifier = Modifier.alpha(0.6f),
                        color = Color.Red.copy(alpha = 0.6f)
                    )
                }
            } else {
                //Todo format date
                Text(
                    text = "Finished on: ${book.finishedReading}",
                    modifier = Modifier.alpha(0.6f),
                    color = Color.Red.copy(alpha = 0.6f)
                )
            }

        }
        Text(text = "Rating", modifier = Modifier.padding(bottom = 3.dp))
        book.rating?.toInt().let {
            RatingBar(rating = it!!) { rating ->
                ratingVal.intValue = rating
                Log.d("TAG", "ShowSimpleForm: ${ratingVal.intValue}")
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        val changedNotes = book.notes != notesText.value
        val changedRating = book.rating?.toInt() != ratingVal.intValue
        val isFinishedTimeStamp =
            if (isFinishedReading.value) Timestamp.now() else book.finishedReading
        val isStartedTimeStamp =
            if (isStartedReading.value) Timestamp.now() else book.startedReading

        val bookUpdate =
            changedNotes || changedRating || isStartedReading.value || isFinishedReading.value

        val bookToUpdate = hashMapOf(
            "finished_reading_at" to isFinishedTimeStamp,
            "started_reading_at" to isStartedTimeStamp,
            "rating" to ratingVal.intValue,
            "notes" to notesText.value
        ).toMap()

        RounderButton(label = "Update") {
            val context = LocalContext.current
            if (bookUpdate) {
                FirebaseFirestore.getInstance().collection("books").document(book.id.toString())
                    .update(bookToUpdate)
                    .addOnCompleteListener {
                        showToast(context, "Book updated")
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }.addOnFailureListener {
                        Log.d("TAG", "ShowSimpleForm: ${it.message}")
                    }
            }
            navController.popBackStack()

        }
        Spacer(modifier = Modifier.width(100.dp))
        val openDialog = remember {
            mutableStateOf(false)
        }
        if (openDialog.value) {
            ShowAlertDialog(
                title = "are you sure you want to delete this book?",
                openDialog = openDialog
            ) {
                val context = LocalContext.current
                FirebaseFirestore.getInstance().collection("books").document(book.id.toString())
                    .delete()
                    .addOnCompleteListener {
                        showToast(context, "Book deleted")
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }.addOnFailureListener {
                        Log.d("TAG", "ShowSimpleForm: ${it.message}")
                    }
            }
        }
        RounderButton(label = "Delete") {
            openDialog.value = true
        }
    }
}

@Composable
fun ShowAlertDialog(
    title: String,
    openDialog: MutableState<Boolean>,
    onYesPressed: @Composable () -> Unit,
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text(text = title) },
            text = { /*TODO*/ },
            confirmButton = { /*TODO*/ },
            dismissButton = { /*TODO*/ }

        )
    }


}

@Composable
fun ShowBookUpdate(bookInfo: DataOrException<List<MBook>, Boolean, Exception>, bookItemId: String) {
    if (bookInfo.data?.isNotEmpty() == true) {
        Row {
            Spacer(modifier = Modifier.width(43.dp))
            if (bookInfo.data != null) {
                Column(
                    modifier = Modifier.padding(4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CardListItem(book = bookInfo.data!!.first { mBook ->
                        mBook.googleBookId == bookItemId
                    }, onPressDetails = {})
                }
            }
        }
    } else {
        Text(text = "Book not found")
    }

}

@Composable
fun CardListItem(
    book: MBook,
    onPressDetails: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable { },
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Image(
                painter = rememberImagePainter(data = book.photoUrl.toString()),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 120.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                        )
                    )
            )
            Column {
                Text(
                    text = book.title.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(120.dp),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = book.authors.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 2.dp,
                        bottom = 0.dp
                    )
                )

                Text(
                    text = book.publishedDate.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 0.dp,
                        bottom = 8.dp
                    )
                )

            }

        }


    }

}
