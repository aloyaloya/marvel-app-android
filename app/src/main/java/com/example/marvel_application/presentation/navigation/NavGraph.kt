package com.example.marvel_application.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvel_application.presentation.ui.screens.character_card_screen.CharacterCardScreen
import com.example.marvel_application.presentation.ui.screens.main_screen.CharacterViewModel
import com.example.marvel_application.presentation.ui.screens.main_screen.MainScreen

private object Screens {
    object Main {
        fun route() = "main_screen"
    }

    object Character {
        fun characterId(): String = "characterId"
        fun baseRoute() = "character_card_screen/{characterId}"
        fun route(id: Int) = "character_card_screen/$id"
    }
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: CharacterViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Main.route(),
    ) {
        composable(route = Screens.Main.route()) {
            MainScreen(
                onNavigateToCharacterCardScreen = {
                    navController.navigate(Screens.Character.route(it))
                },
                viewModel = viewModel
            )
        }
        composable(
            route = Screens.Character.baseRoute(),
            arguments = listOf(navArgument(Screens.Character.characterId()) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt(Screens.Character.characterId())
            characterId?.let {
                CharacterCardScreen(
                    characterId = characterId,
                    onClick = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
        }
    }
}