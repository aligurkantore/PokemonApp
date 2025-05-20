package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.model.pokemondetail.PokemonDetailResponse
import com.example.pokemonapp.data.model.pokemonlist.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int,
        @Query("offset") offset: Int
    ) : Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Response<PokemonDetailResponse>

}