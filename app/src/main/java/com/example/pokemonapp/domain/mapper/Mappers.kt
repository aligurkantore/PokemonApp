package com.example.pokemonapp.domain.mapper

import com.example.pokemonapp.data.model.pokemonlist.PokemonListItem
import com.example.pokemonapp.domain.model.pokemonlist.PokemonListUI

fun PokemonListItem.mapToPokemonListUI(): PokemonListUI {
    return PokemonListUI(
        name = name,
        imageUrl = url
    )
}