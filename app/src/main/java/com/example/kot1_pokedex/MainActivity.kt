package com.example.kot1_pokedex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kot1_pokedex.data.RetrofitClient
import com.example.kot1_pokedex.databinding.ActivityMainBinding
import com.example.kot1_pokedex.ui.PokemonAdapter
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PokemonAdapter{ pokemon -> }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.pokemonRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.pokemonRecyclerView.adapter = adapter

        loadPokemon()

    }
    private fun loadPokemon() {
        lifecycleScope.launch {
            try {
                val pokemonList = RetrofitClient.api.getPokemonList()
                    .filter { it.pokedexId != null }
                    .slice(1..20)
                adapter.submitList(pokemonList)
            } catch (e: IOException) {
                Log.e("MainActivity", "Erreur réseau", e)
                Toast.makeText(this@MainActivity, "Pas de connexion Internet", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("MainActivity", "Erreur inattendue", e)
                Toast.makeText(this@MainActivity, "Une erreur est survenue", Toast.LENGTH_LONG).show()
            }
        }
    }
}