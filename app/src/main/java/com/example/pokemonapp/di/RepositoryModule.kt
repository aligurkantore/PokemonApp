package com.example.pokemonapp.di

import com.example.pokemonapp.data.remote.Service
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        service: Service
    ): PokemonRepository = PokemonRepositoryImpl(
        service = service
    )
}