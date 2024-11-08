package com.example.marvel_application.presentation.ui.screens.main_screen

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val quote: String,
    val imageUrl: String
)

object MarvelCharacters {
    val characters = listOf(
        MarvelCharacter(
            id = 0,
            name = "Deadpool",
            quote = "Please don’t make the super suit green...or animated!",
            imageUrl = "https://iili.io/JMnAfIV.png"
        ),
        MarvelCharacter(
            id = 1,
            name = "Iron Man",
            quote = "I AM IRON MAN",
            imageUrl = "https://iili.io/JMnuDI2.png"
        ),
        MarvelCharacter(
            id = 2,
            name = "Spider-Man",
            quote = "In iron suit",
            imageUrl = "https://iili.io/JMnuyB9.png"
        ),
    )
}