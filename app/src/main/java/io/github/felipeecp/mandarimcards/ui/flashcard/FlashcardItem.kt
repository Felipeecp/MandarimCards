package io.github.felipeecp.mandarimcards.ui.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard

@Composable
fun FlashcardItem(flashcard: Flashcard, onClick:()-> Unit) {

    Column(
        modifier = Modifier.padding(16.dp).clickable(onClick=onClick)
    ) {
        Text(text = flashcard.character, style = MaterialTheme.typography.displayLarge)
        Text(text = flashcard.pinyin, style = MaterialTheme.typography.displayMedium)
        Text(text = flashcard.meaning, style = MaterialTheme.typography.displaySmall)
    }

}