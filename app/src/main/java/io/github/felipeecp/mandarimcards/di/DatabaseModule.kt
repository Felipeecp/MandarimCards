package io.github.felipeecp.mandarimcards.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.felipeecp.mandarimcards.data.local.dao.FlashcardDao
import io.github.felipeecp.mandarimcards.data.local.database.FlashcardDatabase
import io.github.felipeecp.mandarimcards.data.repository.FlashcardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = FlashcardDatabase.getDatabase(appContext)

    @Provides
    fun provideFlashcardDao(database: FlashcardDatabase) = database.flashcardDao()

    @Provides
    fun provideFlashcardRepository(flashcardDao: FlashcardDao) = FlashcardRepository(flashcardDao)

}