package io.github.felipeecp.mandarimcards.ui.flashcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard
import io.github.felipeecp.mandarimcards.data.repository.FlashcardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardViewModel @Inject constructor(private val repository: FlashcardRepository): ViewModel() {

    val allFlashcards: LiveData<List<Flashcard>> = repository.allFlashcards

    fun getFlashcardById(id:Int): Flow<Flashcard?>{
        return repository.getFlashCardById(id)
    }

    fun insert(flashcard: Flashcard) = viewModelScope.launch {
        repository.insert(flashcard)
    }

    fun update(flashcard: Flashcard) = viewModelScope.launch {
        repository.update(flashcard)
    }

    fun delete(flashcard: Flashcard) = viewModelScope.launch {
        repository.delete(flashcard)
    }

}