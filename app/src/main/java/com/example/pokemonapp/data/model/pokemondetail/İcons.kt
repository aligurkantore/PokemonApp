package com.example.pokemonapp.data.model.pokemondetail


import com.google.gson.annotations.SerializedName

data class İcons(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any
)