package com.example.pokemonapp.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemonapp.ui.components.PokemonListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onEvent: (HomeEvent) -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value
    val lazyPagingItems = state.pokemonList.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
       // viewModel.setEvent(HomeEvent.LoadPokemon)

        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetail -> {
                    onEvent(HomeEvent.NavigateToDetailClick(effect.name))
                }
                is HomeEffect.ShowFail -> {
                    Toast.makeText(context, context.getString(effect.message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier) {
        items(lazyPagingItems.itemCount) { index ->
            val pokemon = lazyPagingItems[index]
            pokemon?.let {
                PokemonListItem(
                    pokemon = it,
                    onItemClick = { name ->
                        onEvent(HomeEvent.NavigateToDetailClick(name))
                    }
                )
            }
        }
    }
}





