package io.github.felipeecp.mandarimcards.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.felipeecp.mandarimcards.data.local.dao.FlashcardDao
import io.github.felipeecp.mandarimcards.data.local.entities.Flashcard

@Database(entities = [Flashcard::class], version = 1, exportSchema = false)
abstract class FlashcardDatabase: RoomDatabase(){

    abstract fun flashcardDao(): FlashcardDao

    companion object{

        @Volatile
        private var INSTANCE: FlashcardDatabase? = null

        fun getDatabase(context: Context): FlashcardDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardDatabase::class.java,
                    "flashcard_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}