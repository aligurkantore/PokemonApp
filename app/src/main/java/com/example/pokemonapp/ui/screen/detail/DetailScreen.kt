package com.example.pokemonapp.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.ui.components.StatBar

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
                    Toast.makeText(context, context.getString(effect.message), Toast.LENGTH_SHORT)
                        .show()
                }

                DetailEffect.GoToBack -> {
                    // back action
                }
            }
        }
    }

    state.pokemonDetail?.let { detail ->
        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                AsyncImage(
                    model = detail.sprites.frontDefault,
                    contentDescription = detail.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val statList = listOf(
                "HP" to "hp",
                "ATTACK" to "attack",
                "DEFENSE" to "defense",
                "SPECIAL ATTACK" to "special-attack",
                "SPECIAL DEFENSE" to "special-defense",
                "SPEED" to "speed"
            )

            statList.forEachIndexed { index, (label, key) ->
                val stat = detail.stats.find { it.stat.name == key }
                StatBar(
                    statName = label,
                    statValue = stat?.baseStat ?: 0,
                    statMaxValue = 255,
                )
                if (index != statList.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    }
}
