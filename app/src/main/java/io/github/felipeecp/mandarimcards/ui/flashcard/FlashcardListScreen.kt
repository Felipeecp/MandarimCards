@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.felipeecp.mandarimcards.ui.flashcard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun FlashcardListScreen(viewModel: FlashCardViewModel, navController: NavHostController,innerPadding:PaddingValues) {

    val flashcard by viewModel.allFlashcards.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flashcards") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate("flashcard_form")}) {
                Text(text = "+")
            }
        },
        content = {
            LazyColumn(modifier = Modifier.padding(it).fillMaxSize()) {
                items(flashcard){flashcard ->
                    FlashcardItem(
                        flashcard,
                        onClick={
                            navController.navigate("flashcard_form/${flashcard.id}")
                        }
                    )
                }
            }
        }
    )
}