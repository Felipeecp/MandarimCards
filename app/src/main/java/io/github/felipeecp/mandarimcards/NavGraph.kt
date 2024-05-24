package io.github.felipeecp.mandarimcards

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.felipeecp.mandarimcards.ui.flashcard.FlashCardViewModel
import io.github.felipeecp.mandarimcards.ui.flashcard.FlashcardFormScreen
import io.github.felipeecp.mandarimcards.ui.flashcard.FlashcardListScreen

@Composable
fun NavGraph(navController: NavHostController, viewModel: FlashCardViewModel, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "flashcard_list") {
        composable("flashcard_list") {
            FlashcardListScreen(viewModel = viewModel, navController = navController, innerPadding)
        }
        composable(route = "flashcard_form/{flashcardId}", arguments = listOf(navArgument("flashcardId"){defaultValue=-1})
        ){ backStateEntry->
            val flashcardId = backStateEntry.arguments?.getInt("flashcardId")?:-1
            FlashcardFormScreen(viewModel = viewModel,navController,innerPadding,flashcardId)
        }
    }
}