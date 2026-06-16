package com.example.kot1_pokedex.data

import com.example.kot1_pokedex.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface TyradexApi {

    @GET("api/v1/pokemon/{idOrName}")
    suspend fun getPokemon(@Path("idOrName") idOrName: String): Pokemon

    @GET("api/v1/pokemon")
    suspend fun getPokemonList(): List<Pokemon>

}