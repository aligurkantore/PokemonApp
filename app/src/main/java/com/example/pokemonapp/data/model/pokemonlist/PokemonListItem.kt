package com.example.pokemonapp.data.model.pokemonlist


import com.google.gson.annotations.SerializedName

data class PokemonListItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)