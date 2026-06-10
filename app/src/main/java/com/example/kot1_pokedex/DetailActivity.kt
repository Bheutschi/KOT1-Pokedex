package com.example.kot1_pokedex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil3.load
import com.example.kot1_pokedex.data.RetrofitClient
import com.example.kot1_pokedex.databinding.ActivityDetailBinding
import com.example.kot1_pokedex.model.Pokemon
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        binding.backButton.setOnClickListener { finish() }

        val pokemonId = intent.getIntExtra("POKEMON_ID", -1)
        if (pokemonId == -1) {
            Toast.makeText(this, "Pokémon introuvable", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        loadPokemonDetail(pokemonId)
    }

    private fun loadPokemonDetail(id: Int) {
        lifecycleScope.launch {
            try {
                val pokemon = RetrofitClient.api.getPokemon(id.toString())
                bindPokemon(pokemon)
            } catch (e: IOException) {
                Log.e("DetailActivity", "Erreur réseau", e)
                Toast.makeText(this@DetailActivity, "Pas de connexion Internet", Toast.LENGTH_LONG)
                    .show()
            } catch (e: Exception) {
                Log.e("DetailActivity", "Erreur inattendue", e)
                Toast.makeText(this@DetailActivity, "Une erreur est survenue", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun bindPokemon(pokemon: Pokemon) {
        binding.detailName.text = pokemon.name?.fr ?: "Inconnu"
        binding.detailNumber.text = getString(
            R.string.pokemon_number,
            pokemon.pokedexId.toString().padStart(3, '0')
        )
        binding.detailImage.load(pokemon.sprites?.regular)
        binding.detailHeight.text = pokemon.height ?: "?"
        binding.detailWeight.text = pokemon.weight ?: "?"

        binding.detailTypeChipGroup.removeAllViews()
        pokemon.types?.forEach { type ->
            val chip = Chip(this).apply {
                text = type.name ?: ""
                isClickable = false
                chipStrokeWidth = 0f
                setTextColor(android.graphics.Color.WHITE)
                chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                    TypeColors.forType(type.name ?: "")
                )
            }
            binding.detailTypeChipGroup.addView(chip)
        }

        bindStat(binding.statHp, "HP", pokemon.stats?.hp, 0xFF4CAF50.toInt())
        bindStat(binding.statAtk, "Attaque", pokemon.stats?.atk, 0xFFF44336.toInt())
        bindStat(binding.statDef, "Défense", pokemon.stats?.def, 0xFFFFC107.toInt())
        bindStat(binding.statSpeAtk, "Attaque spé", pokemon.stats?.speAtk, 0xFF9C27B0.toInt())
        bindStat(binding.statSpeDef, "Défense spé", pokemon.stats?.speDef, 0xFF3F51B5.toInt())
        bindStat(binding.statSpeed, "Vitesse", pokemon.stats?.speed, 0xFFFF9800.toInt())
    }

    private fun bindStat(
        statView: com.example.kot1_pokedex.databinding.ItemStatBinding,
        label: String,
        value: Int?,
        color: Int
    ) {
        statView.statLabel.text = label
        statView.statValue.text = (value ?: 0).toString()
        statView.statBar.progress = value ?: 0
        statView.statBar.progressTintList =
            android.content.res.ColorStateList.valueOf(color)
    }
}