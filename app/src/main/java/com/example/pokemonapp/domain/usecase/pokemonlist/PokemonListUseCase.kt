package com.example.pokemonapp.domain.usecase.pokemonlist

import androidx.paging.PagingData
import com.example.pokemonapp.data.model.pokemonlist.PokemonListItem
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): Flow<PagingData<PokemonListItem>> {
        return repository.getPokemonList()
    }
}