package io.github.felipeecp.mandarimcards.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {

    @Query("SELECT * FROM flashcards")
    fun getAllFlashcards(): LiveData<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE id = :id")
    fun getFlashcardById(id:Int): Flow<Flashcard?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashcard(flashcard: Flashcard)

    @Update
    suspend fun updateFlashcard(flashcard: Flashcard)

    @Delete
    suspend fun deleteFlashcard(flashcard: Flashcard)

}