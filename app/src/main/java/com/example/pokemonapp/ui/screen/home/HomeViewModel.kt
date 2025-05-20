package com.example.pokemonapp.ui.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.common.base.BaseViewModel
import com.example.pokemonapp.common.base.Effect
import com.example.pokemonapp.common.base.Event
import com.example.pokemonapp.common.base.State
import com.example.pokemonapp.common.util.FailViewType
import com.example.pokemonapp.data.model.pokemonlist.PokemonListItem
import com.example.pokemonapp.domain.usecase.pokemonlist.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    init {
        loadPokemon()
    }

    override fun setInitialState() = HomeState()

    override fun handleEvents(event: HomeEvent) {
        when (event) {
           // is HomeEvent.LoadPokemon -> loadPokemon()
            is HomeEvent.NavigateToDetailClick -> {
                setEffect { HomeEffect.NavigateToDetail(event.name) }
            }

        }
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            try {
                val result = pokemonListUseCase()
                    .cachedIn(viewModelScope)
                setState { copy(isLoading = false, pokemonList = result, error = null) }
            } catch (e: Exception) {
                setState { copy(isLoading = false, error = e.localizedMessage) }
            }
        }
    }
}

sealed interface HomeEvent : Event {
    //data object LoadPokemon : HomeEvent
    data class NavigateToDetailClick(val name: String) : HomeEvent
}

data class HomeState(
    val isLoading: Boolean = false,
    val pokemonList: Flow<PagingData<PokemonListItem>> = emptyFlow(),
    val error: String? = null
) : State

sealed interface HomeEffect : Effect {
    data class NavigateToDetail(val name: String) : HomeEffect
    data class ShowFail(val message: Int, val failViewType: FailViewType) : HomeEffect
}
