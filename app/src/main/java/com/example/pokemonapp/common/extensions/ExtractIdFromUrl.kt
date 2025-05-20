package com.example.pokemonapp.common.extensions

fun String.extractIdFromUrl(): Int {
    return this.trimEnd('/').split("/").last().toIntOrNull() ?: 0
}

fun String.getPokemonImageUrl(): String {
    val id = this.extractIdFromUrl()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}