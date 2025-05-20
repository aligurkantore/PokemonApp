package com.example.pokemonapp.data.repository

import android.util.Log
import androidx.paging.PagingData
import com.example.pokemonapp.common.base.BaseRepository
import com.example.pokemonapp.common.util.Resource
import com.example.pokemonapp.common.util.onSuccess
import com.example.pokemonapp.data.model.pokemonlist.PokemonListItem
import com.example.pokemonapp.data.remote.Service
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val service: Service
) : BaseRepository(), PokemonRepository {

    override suspend fun getPokemonList(): Flow<PagingData<PokemonListItem>> =
        safeApiCallPaging { page, pageSize ->
            safeApiCall {
                service.getPokemonList(
                    limit = pageSize,
                    offset = (page - 1) * pageSize
                )
            }.also { result ->
                when (result) {
                    is Resource.Success -> Log.d("API_DEBUG", "API call successful")
                    is Resource.Fail -> Log.e("API_DEBUG", "API call failed: ${result.message}")
                }
            }.onSuccess { response ->
                response.pokemonListItems
            }
        }

    override suspend fun getPokemonDetail(pokemonName: String) = safeApiCall {
        service.getPokemonDetail(pokemonName)
    }
}