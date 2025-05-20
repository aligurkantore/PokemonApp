package com.example.pokemonapp.ui.screen.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    pokemonName: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {

        viewModel.loadPokemonDetail(pokemonName)

        viewModel.effect.collect { effect ->
            when (effect) {
                is DetailEffect.ShowFail -> {
                    Toast.makeText(context, context.getString(effect.message), Toast.LENGTH_SHORT).show()
                }

                DetailEffect.GoToBack -> {
                    // back action
                }
            }
        }
    }

    state.pokemonDetail?.let { detail ->
        Log.d("agt", "DetailScreen: ${detail.name}")
        Log.d("agt", "DetailScreen: ${detail.weight}")
        Log.d("agt", "DetailScreen: ${detail.height}")
    }

}
