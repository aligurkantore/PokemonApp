package com.example.pokemonapp.ui.screen.detail

import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.common.base.BaseViewModel
import com.example.pokemonapp.common.base.Effect
import com.example.pokemonapp.common.base.Event
import com.example.pokemonapp.common.base.State
import com.example.pokemonapp.common.util.FailViewType
import com.example.pokemonapp.common.util.Resource
import com.example.pokemonapp.data.model.pokemondetail.PokemonDetailResponse
import com.example.pokemonapp.domain.usecase.pokemondetail.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase
) : BaseViewModel<DetailEvent, DetailState, DetailEffect>() {

    override fun setInitialState() = DetailState()

    override fun handleEvents(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnBackClick -> {
                setEffect { DetailEffect.GoToBack }
            }
        }
    }

    fun loadPokemonDetail(pokemonName: String) = viewModelScope.launch {
        pokemonDetailUseCase.invoke(pokemonName)
            .onStart { setState { copy(isLoading = true) } }
            .onCompletion { setState { copy(isLoading = false) } }
            .collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        val pokemonDetail = result.data
                        setState { copy(isLoading = false, pokemonDetail = pokemonDetail) }
                    }

                    is Resource.Fail -> {
                        setEffect {
                            DetailEffect.ShowFail(
                                result.message,
                                result.failViewType
                            )
                        }
                    }
                }
            }
    }


}

sealed interface DetailEvent : Event {
    data object OnBackClick : DetailEvent
}

data class DetailState(
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetailResponse? = null
) : State

sealed interface DetailEffect : Effect {
    data object GoToBack : DetailEffect
    data class ShowFail(val message: Int, val failViewType: FailViewType) : DetailEffect
}