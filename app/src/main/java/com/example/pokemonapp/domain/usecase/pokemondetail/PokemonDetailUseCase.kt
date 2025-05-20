package com.example.pokemonapp.domain.usecase.pokemondetail

import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {

    operator fun invoke(pokemonName: String) = flow {
        emit(
            repository.getPokemonDetail(pokemonName)
        )
    }
}