package io.github.felipeecp.mandarimcards.data.repository

import androidx.lifecycle.LiveData
import io.github.felipeecp.mandarimcards.data.local.dao.FlashcardDao
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlashcardRepository @Inject constructor(private val flashcardDao: FlashcardDao){

    val allFlashcards: LiveData<List<Flashcard>> = flashcardDao.getAllFlashcards()

    fun getFlashCardById(id:Int): Flow<Flashcard?> {
        return flashcardDao.getFlashcardById(id)
    }

    suspend fun insert(flashcard: Flashcard) {
        flashcardDao.insertFlashcard(flashcard)
    }

    suspend fun update(flashcard: Flashcard){
        flashcardDao.updateFlashcard(flashcard)
    }

    suspend fun delete(flashcard: Flashcard){
        flashcardDao.deleteFlashcard(flashcard)
    }

}