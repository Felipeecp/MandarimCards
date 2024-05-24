@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.felipeecp.mandarimcards.ui.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun FlashcardListScreen(viewModel: FlashCardViewModel, navController: NavHostController, innerPadding: PaddingValues) {
    val flashcards by viewModel.allFlashcards.observeAsState()
    val (showTranslation, setShowTranslation) = remember { mutableStateOf(false) }
    val (currentFlashcardIndex, setCurrentFlashcardIndex) = remember { mutableStateOf(0)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flashcards") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("flashcard_form/-1") }) {
                Text(text = "+")
            }
        },
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(flashcards.isNullOrEmpty()){
                    Text(text = "No flashcards available")
                }else{
                    val currentFlashcard =  flashcards!![currentFlashcardIndex]
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(onClick = { setShowTranslation(!showTranslation) }),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    ){
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = currentFlashcard.character)
                            Text(text = currentFlashcard.pinyin)
                            if(showTranslation){
                                Text(text = currentFlashcard.meaning)
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(onClick = {
                                        setCurrentFlashcardIndex((currentFlashcardIndex+1)% flashcards!!.size)
                                        setShowTranslation(false)
                                    }) {
                                        Text(text = "Correto")
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Button(onClick = {
                                        setCurrentFlashcardIndex((currentFlashcardIndex+1)% flashcards!!.size)
                                        setShowTranslation(false)
                                    }){
                                        Text(text = "Incorreto")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}