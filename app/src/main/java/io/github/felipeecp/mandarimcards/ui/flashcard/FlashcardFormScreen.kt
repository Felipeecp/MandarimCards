@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.felipeecp.mandarimcards.ui.flashcard

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard
import io.github.felipeecp.mandarimcards.ui.theme.MandarimCardsTheme

@Composable
fun FlashcardFormScreen(
    viewModel: FlashCardViewModel,
    navController: NavHostController,
    innerPadding:PaddingValues,
    flashcardId:Int
) {

    val flashcard by viewModel.getFlashcardById(flashcardId).collectAsState(initial = null)
    var character by rememberSaveable { mutableStateOf("") }
    var pinyin by rememberSaveable { mutableStateOf("") }
    var meaning by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(flashcard) {
        flashcard?.let{
            character = it.character
            pinyin = it.pinyin
            meaning = it.meaning
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TextField(
            value = character,
            onValueChange = {character = it},
            label = { Text("Character") }
        )
        TextField(
            value = pinyin,
            onValueChange = {pinyin = it},
            label = { Text("Pinyin") }
        )
        TextField(
            value = meaning,
            onValueChange = {meaning = it},
            label = { Text("Meaning") }
        )
        Row {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick= {
                val newFlashcard = Flashcard(character=character, pinyin = pinyin, meaning = meaning)
                if(flashcard == null){
                    viewModel.insert(newFlashcard)
                }else{
                    viewModel.update(newFlashcard.copy(id = flashcardId))
                }
                navController.popBackStack()
            }){
                Text(text = if (flashcard == null) "Adicionar" else "Atualizar")
            }
            if(flashcard != null){
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick= {
                        flashcard?.let {viewModel.delete(it)}
                        navController.popBackStack()
                    },
                )
                {
                    Text(text = "Apagar")
                }
            }
        }

    }
}