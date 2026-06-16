package com.example.kot1_pokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kot1_pokedex.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashTitle.alpha = 0f

        binding.splashLogo.animate()
            .rotationBy(720f)
            .setDuration(1000)
            .start()

        binding.splashTitle.animate()
            .alpha(1f)
            .setStartDelay(300)
            .setDuration(700)
            .withEndAction { goToMain() }
            .start()
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}