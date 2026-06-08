package com.example.kot1_pokedex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kot1_pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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