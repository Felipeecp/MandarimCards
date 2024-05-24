package io.github.felipeecp.mandarimcards.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.felipeecp.mandarimcards.NavGraph
import io.github.felipeecp.mandarimcards.ui.flashcard.FlashCardViewModel
import io.github.felipeecp.mandarimcards.ui.theme.MandarimCardsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val flashCardViewModel: FlashCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MandarimCardsTheme {
                val navController = rememberNavController()
                Scaffold (
                    content = { innerPadding ->
                        NavGraph(
                            navController = navController,
                            viewModel = flashCardViewModel,
                            innerPadding=innerPadding
                        )
                    }
                )
            }
        }
    }
}