package com.example.pokemonapp.data.model.pokemondetail


import com.google.gson.annotations.SerializedName

data class AbilityXX(
    @SerializedName("ability")
    val ability: Any,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)