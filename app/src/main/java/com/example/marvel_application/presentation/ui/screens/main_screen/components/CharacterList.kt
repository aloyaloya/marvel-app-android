package com.example.marvel_application.presentation.ui.screens.main_screen.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import com.example.marvel_application.R
import com.example.marvel_application.presentation.ui.screens.main_screen.CharacterViewModel
import kotlin.math.absoluteValue

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel,
    onClick: (id: Int) -> Unit
) {
    val characters by viewModel.characters.collectAsState()

    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.large_padding))
    ) {
        itemsIndexed(characters) { index, character ->
            val itemOffset = lazyListState.calculateCurrentOffsetForItem(index)
            val scale = 1f - ((itemOffset.absoluteValue / 3000f).coerceIn(0f, 0.1f))

            CharacterCard(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    },
                id = character.id,
                viewModel = viewModel,
                onClick = onClick
            )
        }
    }
}

@Composable
fun LazyListState.calculateCurrentOffsetForItem(index: Int): Float {
    val layoutInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    return layoutInfo?.offset?.toFloat() ?: 0f
}