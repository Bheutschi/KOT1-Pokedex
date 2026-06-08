package com.example.kot1_pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.kot1_pokedex.R
import com.example.kot1_pokedex.databinding.ItemPokemonBinding
import com.example.kot1_pokedex.model.Pokemon
import com.google.android.material.chip.Chip

class PokemonAdapter(
    private val onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DIFF) {

    inner class PokemonViewHolder(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            binding.pokemonName.text = pokemon.name?.fr ?: "Inconnu"
            binding.pokemonNumber.text = binding.root.context.getString(
                R.string.pokemon_number,
                pokemon.pokedexId.toString().padStart(3, '0')
            )
            binding.pokemonImage.load(pokemon.sprites?.regular)

            binding.typeChipGroup.removeAllViews()
            pokemon.types?.forEach { type ->
                val chip = Chip(binding.root.context).apply {
                    text = type.name ?: ""
                    isClickable = false
                    chipStrokeWidth = 0f
                    setTextColor(android.graphics.Color.WHITE)
                    chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        colorForType(type.name)
                    )
                }
                binding.typeChipGroup.addView(chip)
            }

            binding.root.setOnClickListener { onClick(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(old: Pokemon, new: Pokemon) =
                old.pokedexId == new.pokedexId

            override fun areContentsTheSame(old: Pokemon, new: Pokemon) =
                old == new
        }
    }

    private fun colorForType(typeName: String?): Int = when (typeName?.lowercase()) {
        "plante" -> 0xFF4CAF50.toInt()
        "poison" -> 0xFF7C4DFF.toInt()
        "feu" -> 0xFFF44336.toInt()
        "eau" -> 0xFF2196F3.toInt()
        "électrik", "electrik" -> 0xFFFFC107.toInt()
        "vol" -> 0xFF90CAF9.toInt()
        "insecte" -> 0xFF9E9D24.toInt()
        "normal" -> 0xFF9E9E9E.toInt()
        else -> 0xFF607D8B.toInt()
    }

}