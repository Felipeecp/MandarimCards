package io.github.felipeecp.mandarimcards.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class Flashcard (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val character: String,
    val pinyin: String,
    val meaning: String
)