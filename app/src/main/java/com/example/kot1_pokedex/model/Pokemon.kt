package com.example.kot1_pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("pokedex_id") val pokedexId: Int?,
    val generation: Int?,
    val category: String?,
    val name: PokemonName?,
    val sprites: PokemonSprites?,
    val types: List<PokemonType>?,
    val stats: PokemonStats?,
    val height: String?,
    val weight: String?
)

data class PokemonName(
    val fr: String?,
    val en: String?,
    val jp: String?
)

data class PokemonSprites(
    val regular: String?,
    val shiny: String?
)

data class PokemonType(
    val name: String?,
    val image: String?
)

data class PokemonStats(
    val hp: Int?,
    val atk: Int?,
    val def: Int?,
    @SerializedName("spe_atk") val speAtk: Int?,
    @SerializedName("spe_def") val speDef: Int?,
    @SerializedName("vit") val speed: Int?
)