package com.example.pokemonapp.domain.repository

import androidx.paging.PagingData
import com.example.pokemonapp.common.util.Resource
import com.example.pokemonapp.data.model.pokemondetail.PokemonDetailResponse
import com.example.pokemonapp.data.model.pokemonlist.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(): Flow<PagingData<PokemonListItem>>

    suspend fun getPokemonDetail(pokemonName: String) :Resource<PokemonDetailResponse>
}