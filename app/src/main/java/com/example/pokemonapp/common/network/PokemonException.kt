package com.example.pokemonapp.common.network

import com.example.pokemonapp.common.util.FailViewType

class PagingFail(override val message: String, val failViewType: FailViewType) : Exception(message)