package com.example.kot1_pokedex

import androidx.core.graphics.toColorInt

object TypeColors {

        private val colors: Map<String, Int> = mapOf(
            "normal" to "#A8A77A".toColorInt(),
            "feu" to "#EE8130".toColorInt(),
            "eau" to "#6390F0".toColorInt(),
            "plante" to "#7AC74C".toColorInt(),
            "électrik" to "#F7D02C".toColorInt(),
            "glace" to "#96D9D6".toColorInt(),
            "combat" to "#C22E28".toColorInt(),
            "poison" to "#A33EA1".toColorInt(),
            "sol" to "#E2BF65".toColorInt(),
            "vol" to "#A98FF3".toColorInt(),
            "psy" to "#F95587".toColorInt(),
            "insecte" to "#A6B91A".toColorInt(),
            "roche" to "#B6A136".toColorInt(),
            "spectre" to "#735797".toColorInt(),
            "dragon" to "#6F35FC".toColorInt(),
            "ténèbres" to "#705746".toColorInt(),
            "acier" to "#B7B7CE".toColorInt(),
            "fée" to "#D685AD".toColorInt()
        )

        private val default = "#607D8B".toColorInt()

        fun forType(typeName: String?): Int =
            colors[typeName?.lowercase()] ?: default
    }