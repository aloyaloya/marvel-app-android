package com.example.marvel_application.presentation.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.marvel_application.R
import com.example.marvel_application.presentation.ui.screens.main_screen.components.CharactersList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToCharacterCardScreen: (id: Int) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.ic_main_background),
                    contentScale = ContentScale.FillBounds,
                )
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.logo_height)
                ),
                painter = painterResource(id = R.drawable.ic_marvel_logo),
                contentDescription = stringResource(
                    id = R.string.content_description_marvel_logo
                ),
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.large_spacer_height))
            )
            Text(
                text = stringResource(id = R.string.choose_your_hero),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.extra_large_spacer_height))
            )
            CharactersList(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToCharacterCardScreen
            )
        }
    }
}